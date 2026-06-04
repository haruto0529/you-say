package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.Question;
import com.example.you_say_app.model.dto.QuestionDto;

@Repository
public class QuestionDao extends SuperDao {

	private static final String sql1 = "SELECT question_id,questions.quote_id,question_text,answer_text FROM questions";

	public Question putInQuestion() {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1);
				ResultSet rs = ps.executeQuery()) {

			Question question = new Question();
			while (rs.next()) {
				QuestionDto questionDto = new QuestionDto();
				questionDto.setQuestionId(rs.getString("question_id"));
				questionDto.setQuoteId(rs.getString("quote_id"));
				questionDto.setQuestionText(rs.getString("question_text"));
				questionDto.setAnswerText(rs.getString("answer_text"));
				question.setQuestion(questionDto);
			}
			return question;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
