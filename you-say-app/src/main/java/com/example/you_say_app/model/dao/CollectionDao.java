package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.OutputCollectionDto;

@Repository // このクラスをDB操作用のBeanとしてSpringに登録
public class CollectionDao extends SuperDao {
	@Autowired
	private LikesDao likesDao;

	// コレクションに1件追加する（user_id と quote_id を登録）
	public int insertCollection(int user_id, int quote_id) {
		int ret = 0; // 更新された行数を返す（成功なら1）
		String sql = "INSERT INTO you_say.collecton_quotes (user_id, quote_id) VALUES (?, ?)";

		// try-with-resources：接続とステートメントを自動でクローズ
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, user_id);
			ps.setInt(2, quote_id);

			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 指定ユーザーが集めたコレクションを取得するメソッド
	public List<OutputCollectionDto> collectionDisplay(int userId) {
		List<OutputCollectionDto> collects = new ArrayList<>();
		// collecton_quotes と quotes を quote_id で結合し、対象ユーザーの分だけ取得
		String sql = "SELECT c.collection_id, c.user_id, c.quote_id, quotes.full_text, c.has_gold_medal, c.created_at, c.deleted_at FROM collecton_quotes AS c JOIN quotes ON c.quote_id = quotes.quote_id WHERE user_id = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId); // WHERE の ? に userId をセット

			try (ResultSet rs = ps.executeQuery()) { // SELECT実行、結果を受け取る

				// 取得した行を1行ずつ読み取る
				while (rs.next()) {
					OutputCollectionDto collect = new OutputCollectionDto();
					collect.setCollectionId(rs.getInt("collection_id"));
					collect.setUserId(rs.getInt("user_id"));
					collect.setQuoteId(rs.getInt("quote_id"));
					collect.setQuote(rs.getString("full_text"));
					collect.setHasGoldMedal(rs.getBoolean("has_gold_medal"));
					collect.setQuote(rs.getString("full_text"));
					collect.setCreatedAt(rs.getObject("created_at", LocalDateTime.class)); // 日時として取得
					collect.setDeletedAt(rs.getObject("deleted_at", LocalDateTime.class));
					collect.setLiked(likesDao.checkLiked(userId, collect.getQuoteId()));
					collect.setLikeCount(likesDao.countLike(collect.getQuoteId()));
					collects.add(collect);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collects; // 1件のDTOを返す
	}

	// 指定ユーザーが、その名言を既にコレクション済みか確認（あればtrue）
	public Boolean canCollect(int userId, int quoteId) {
		Boolean ret = true; // 重複していれば true（初期値は false）

		// user_id と quote_id の組み合わせが既に存在するか調べるSQL
		String sql = "SELECT * FROM  collecton_quotes where user_id = ? AND quote_id = ?;";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId); // WHERE の1つ目の ? に userId をセット
			ps.setInt(2, quoteId); // WHERE の2つ目の ? に quoteId をセット
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 1件でもあれば重複あり
				ret = false;
			}
		} catch (SQLException e) {
			e.printStackTrace(); // エラー内容をコンソールに出力
		}
		return ret; // 重複の有無を返す
	}

}