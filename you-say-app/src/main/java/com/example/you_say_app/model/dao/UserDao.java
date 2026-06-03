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

	public UserDto verify(String mail) {

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {
			ps.setString(1, mail);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					UserDto user = new UserDto();
					user.setUserId(rs.getString("user_id"));
					user.setName(rs.getString("user_name"));
					user.setMail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setSalt(rs.getString("salt"));
					user.setRankId(rs.getString("rank_id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String userRank(String userId) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql2)) {
			ps.setString(1, userId);

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
}
