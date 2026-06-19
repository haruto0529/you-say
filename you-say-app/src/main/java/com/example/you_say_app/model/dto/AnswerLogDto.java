package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class AnswerLogDto {
	private int answerLogId;
	private int questionId;
	private int user_id;
	private boolean result;
	private String userAnswer;
	LocalDateTime createdAt;

	public AnswerLogDto() {

	}

	public AnswerLogDto(int answerLogId, int questionId, int user_id, boolean result, String userAnswer,
			LocalDateTime createdAt) {
		super();
		this.answerLogId = answerLogId;
		this.questionId = questionId;
		this.user_id = user_id;
		this.result = result;
		this.userAnswer = userAnswer;
		this.createdAt = createdAt;
	}

	public int getAnswerLogId() {
		return answerLogId;
	}

	public void setAnswerLogId(int answerLogId) {
		this.answerLogId = answerLogId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
