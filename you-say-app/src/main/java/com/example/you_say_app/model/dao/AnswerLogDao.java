package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class AnswerLogDao extends SuperDao {
	private static String sql1 = "INSERT INTO `you_say`.`answer_logs` ( `question_id`, `user_id`, `result`, `user_answer`) VALUES ( ?, ?, ?, ?)";

	public int setLog(int questionId, int userId, boolean result, String userAnswer) {
		int ret = 0;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {
			ps.setInt(1, questionId);
			ps.setInt(2, userId);
			ps.setBoolean(3, result);
			ps.setString(4, userAnswer);
			
			
			ret =ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;

	}
	
}