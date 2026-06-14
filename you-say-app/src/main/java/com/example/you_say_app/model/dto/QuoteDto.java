package com.example.you_say_app.model.dto;

public class QuoteDto {
	private int question_id;
	private int quote_id;
	private String question_text;
	private String answer_text;

	public QuoteDto() {
	};

	public QuoteDto(int question_id, int quote_id, String question_text, String answer_text) {
		super();
		this.question_id = question_id;
		this.quote_id = quote_id;
		this.question_text = question_text;
		this.answer_text = answer_text;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public int getQuote_id() {
		return quote_id;
	}

	public void setQuote_id(int quote_id) {
		this.quote_id = quote_id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public String getAnswer_text() {
		return answer_text;
	}

	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}

}
