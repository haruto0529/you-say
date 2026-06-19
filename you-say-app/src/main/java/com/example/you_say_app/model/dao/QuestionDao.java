package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.QuestionDto;

@Repository
public class QuestionDao extends SuperDao {

	private static final String sql1 = "SELECT questions.question_id,questions.quote_id,question_text,answer_text FROM questions WHERE question_id = ?";
	private static final String sql2 = "SELECT * FROM questions ";

	//	問題文と答えとQuoteIdを引き出してくるメソッド
	public QuestionDto putInQuestion(int questionId) {
		QuestionDto questionDto = new QuestionDto();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1)) {
			ps.setInt(1, questionId);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					questionDto.setQuestionId(rs.getInt("question_id"));
					questionDto.setQuoteId(rs.getInt("quote_id"));
					questionDto.setQuestionText(rs.getString("question_text"));
					questionDto.setAnswerText(rs.getString("answer_text"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionDto;

	}

	//	クエスチョンIDを全て持ってくるメソッド
	public List<Integer> getQuestionId() {
		List<Integer> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql2);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(rs.getInt("question_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
