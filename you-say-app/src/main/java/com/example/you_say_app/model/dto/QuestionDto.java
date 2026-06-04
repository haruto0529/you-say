package com.example.you_say_app.model.dto;

public class QuestionDto {
	private String questionId;
	private String quoteId;
	private String questionText;
	private String answerText;

	public QuestionDto() {

	}

	public QuestionDto(String questionId, String quoteId, String questionText, String answerText) {
		super();
		this.questionId = questionId;
		this.quoteId = quoteId;
		this.questionText = questionText;
		this.answerText = answerText;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

}
