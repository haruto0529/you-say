package com.example.you_say_app.model.dto;

public class QuestionDto {
	private int questionId;
	private int quoteId;
	private String questionText;
	private String answerText;

	public QuestionDto() {

	}

	public QuestionDto(int questionId, int quoteId, String questionText, String answerText) {
		super();
		this.questionId = questionId;
		this.quoteId = quoteId;
		this.questionText = questionText;
		this.answerText = answerText;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
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
