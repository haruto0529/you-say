package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.LikeDto;

@Repository
public class LikesDao extends SuperDao {

	//	いいねしたときにインサートするSQL
	private static String sql1 = "INSERT INTO `you_say`.`likes` (`collection_id`) VALUES (?)";

	//	likesテーブルの特定のcollection_idのレコードを表示するSQL
	private static String sql2 = "SELECT * FROM you_say.likes where collection_id=?";

	//collection_idのdeleted_atをnullにするSQL
	private static String sql3 = "UPDATE likes SET deleted_at = null WHERE deleted_at is not null AND collection_id = ? ";

	//collection_idのdeleted_atに時間を入れるSQL	
	private static String sql4 = "UPDATE likes SET deleted_at = CURRENT_TIMESTAMP WHERE deleted_at is null AND collection_id = ? ";

	//	名言ごとのいいね数
	private static String sql5 = "SELECT count(*) as 'likeCount' FROM you_say.likes join collecton_quotes on likes.collection_id=collecton_quotes.collection_id where likes.deleted_at is null and collecton_quotes.quote_id=?";

	//	名言に対していいねしているか
	private static String sql6 = "SELECT *  FROM you_say.likes join collecton_quotes on likes.collection_id=collecton_quotes.collection_id where likes.deleted_at is null and collecton_quotes.user_id=? and quote_id=?";

	//	いいねしたときにインサートするメソッド
	public int setLike(int collectionId) {
		int ret = 0;

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {

			ps.setInt(1, collectionId);
			ret = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	//	likesテーブルの特定のcollection_idのレコードをゲットするメソッド
	public LikeDto getRecord(int collectionId) {
		LikeDto likeDto = null;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql2)) {

			ps.setInt(1, collectionId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					likeDto = new LikeDto();
					likeDto.setCollectionId(collectionId);
					likeDto.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
					likeDto.setUpdateAt(rs.getObject("updated_at", LocalDateTime.class));					;
					likeDto.setDeletedAt(rs.getObject("deleted_at", LocalDateTime.class));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likeDto;
	}

	//	指定したcollection_idのdeleted_atをnullにするメソッド
	public int upDateNull(int collectionId) {
		int ret = 0;

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql3)) {
			ps.setInt(1, collectionId);

			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

	//	指定したcollection_idのdeleted_atに時間を入れるメソッド
	public int upDateTime(int collectionId) {
		int ret = 0;

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql4)) {
			ps.setInt(1, collectionId);

			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

	public int countLike(int quoteId) {
		int ret = 0;

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql5)) {
			ps.setInt(1, quoteId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					ret = rs.getInt("likeCount");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

	public boolean checkLiked(int userId, int quoteId) {
		boolean liked = false;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql6)) {
			ps.setInt(1, userId);
			ps.setInt(2, quoteId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					liked = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liked;

	}

}
