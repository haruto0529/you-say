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
		LikeDto likeDto = new LikeDto();

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql2)) {

			ps.setInt(1, collectionId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					likeDto.setCollectionId(collectionId);
					likeDto.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
<<<<<<< HEAD
					likeDto.setUpdateAt(rs.getObject("update_at", LocalDateTime.class));;
=======
					likeDto.setUpdateAt(rs.getObject("update_at", LocalDateTime.class));
>>>>>>> e720a9f1fe793dd26e681182f2abb765cb3ca2fb
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

}
