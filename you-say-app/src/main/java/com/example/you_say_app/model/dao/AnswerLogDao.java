package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.HistoryDto;

@Repository
public class AnswerLogDao extends SuperDao {
	private static String sql1 = "INSERT INTO `you_say`.`answer_logs` ( `question_id`, `user_id`, `result`, `user_answer`) VALUES ( ?, ?, ?, ?)";

	//	指定したuser_idの履歴テーブルを名言テーブルと問題テーブルとjoinして持ってくる
	private static String getLog = "SELECT * FROM you_say.answer_logs join questions on answer_logs.question_id = questions.question_id  join quotes on questions.quote_id = quotes.quote_id where user_id=? limit 50";

	public int setLog(int questionId, int userId, boolean result, String userAnswer) {
		int ret = 0;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {
			ps.setInt(1, questionId);
			ps.setInt(2, userId);
			ps.setBoolean(3, result);
			ps.setString(4, userAnswer);

			ret = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;

	}

	public List<HistoryDto> getLog(int userId) {
		List<HistoryDto> historyList = new ArrayList<>();

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(getLog)) {
			ps.setInt(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					HistoryDto historyDto = new HistoryDto();
					historyDto.setAnswerLogId(rs.getInt("answer_log_id"));
					historyDto.setAnswerText(rs.getString("answer_text"));
					historyDto.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
					historyDto.setQuestionId(rs.getInt("question_id"));
					historyDto.setQuestionText(rs.getString("question_text"));
					historyDto.setQuoteId(rs.getInt("quote_id"));
					historyDto.setResult(rs.getBoolean("result"));
					historyDto.setUserAnswer(rs.getString("user_answer"));

					historyList.add(historyDto);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historyList;

	}

}