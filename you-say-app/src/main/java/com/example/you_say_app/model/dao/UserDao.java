package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.UserDto;

@Repository // DB操作用のBeanとしてSpringに登録
public class UserDao extends SuperDao {

	// メールで1件取得（論理削除されていないユーザーのみ）
	private static final String sql1 = "SELECT users.user_id,users.user_name,users.password,users.salt,users.email,users.rank_id FROM users WHERE  users.email = ? AND users.deleted_at IS NULL";
	// user_id からランク名を取得
	private static final String sql2 = "SELECT rank_name FROM users,ranks WHERE users.rank_id=ranks.rank_id AND user_id = ?";
	// user_id でユーザーを1件取得
	private static final String sql3 = "SELECT *  FROM users WHERE user_id= ?";

	// メールアドレスでユーザーを検索し、いれば情報をDTOで返す（なければnull）
	public UserDto verify(String mail) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {
			ps.setString(1, mail); // WHERE email = ? に mail をセット
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) { // 1件見つかったら
					UserDto user = new UserDto();
					user.setUserId(rs.getInt("user_id"));
					user.setName(rs.getString("user_name"));
					user.setMail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setSalt(rs.getString("salt"));
					user.setRankId(rs.getInt("rank_id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 見つからない or エラー時は null
	}

	// 新規ユーザーを登録（成功した行数を返す）
	public int userRegister(String name, String mail, String password) {
		int ret = 0;
		String sql = "INSERT INTO `you_say`.`users` (`user_name`, `email`, `password`) VALUES (?, ?, ?);";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, password);
			ret = ps.executeUpdate(); // INSERT実行、入った行数が返る
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// メールアドレスが既に登録済みか確認（あればtrue）
	public Boolean isMailDuplication(String mail) {
		Boolean ret = false;
		String sql = "SELECT * FROM users where email = ?;";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 1件でもあれば重複
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// ユーザー名が既に登録済みか確認（あればtrue）
	public Boolean isNameDuplication(String name) {
		Boolean ret = false;
		String sql = "SELECT * FROM users where user_name = ?;";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// user_id からランク名を取得（なければnull）
	public String userRank(int userId) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql2)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getString("rank_name");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// user_id からユーザーの名前とランクIDを取得
	public UserDto getUserInfo(int userId) {
		UserDto userDto = new UserDto();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql3)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					userDto.setName(rs.getString("user_name"));
					userDto.setRankId(rs.getInt("rank_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userDto;
	}
}
