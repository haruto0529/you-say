package com.example.you_say_app.model;

import java.util.ArrayList;

import com.example.you_say_app.model.dto.QuestionDto;

public class Question {
	private ArrayList<QuestionDto> question = new ArrayList<>();

	public Question() {
	}

	public ArrayList<QuestionDto> getQuestion() {
		return question;
	}

	public QuestionDto getTopQuestion() {
		return question.get(0);
	}

	public void deleteTopQuestion() {
		this.question.remove(0);
	}

	public void setQuestion(QuestionDto questiondto) {
		this.question.add(questiondto);
	}

}
