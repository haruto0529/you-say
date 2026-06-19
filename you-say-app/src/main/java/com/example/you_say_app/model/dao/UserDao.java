package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.UserDto;

@Repository
public class UserDao extends SuperDao {

	private static final String sql1 = "SELECT users.user_id,users.user_name,users.password,users.salt,users.email,users.rank_id FROM users WHERE  users.email = ? AND users.deleted_at IS NULL";
	private static final String sql2 = "SELECT rank_name FROM users,ranks WHERE users.rank_id=ranks.rank_id AND user_id = ?";
	private static final String sql3 = "SELECT *  FROM users WHERE user_id= ?";

	//DBに一致するメールアドレス存在する場合、対応するユーザーの情報を取得
	public UserDto verify(String mail) {

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {
			ps.setString(1, mail);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
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
		return null;
	}

	public UserDto userRegister(String name, String mail, String password) {
		int ret = 0;

		String sql = "INSERT INTO `you_say`.`users` (`user_name`, `email`, `password`) VALUES (?, ?, ?);";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, password);
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return verify(mail);

	}

	//メールアドレスがすでに存在するかどうか確認するメソッド
	public Boolean isMailDuplication(String mail) {
		Boolean ret = false;
		String sql = "SELECT * FROM users where email = ?;";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

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

	//	ユーザの名前とランクIDを持ってくるメソッド
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
