package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class HistoryDto {
	private int answerLogId;
	private int questionId;
	private int quoteId;
	LocalDateTime createdAt;
	private String questionText;
	private String userAnswer;
	private boolean result;
	private String answerText;

	public HistoryDto() {

	}

	public HistoryDto(int answerLogId, int questionI, int quoteId, LocalDateTime createdAt, String questionText,
			String userAnswer, boolean result, String answerText) {
		super();
		this.answerLogId = answerLogId;
		this.questionId = questionI;
		this.quoteId = quoteId;
		this.createdAt = createdAt;
		this.questionText = questionText;
		this.userAnswer = userAnswer;
		this.result = result;
		this.answerText = answerText;
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

	public void setQuestionId(int questionI) {
		this.questionId = questionI;
	}

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

}
