package com.example.you_say_app.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.you_say_app.model.dao.QuestionDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizeController {
	@Autowired
	private QuestionDao questionDao;

	@GetMapping("/quize")
	public String quize(@SessionAttribute(name = "questions", required = false) List<Integer> questionsList,
			Model model, HttpSession session) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		if (questionsList == null) {
			questionsList = questionDao.getQuestionId();
			Collections.shuffle(questionsList);
			session.setAttribute("questions", questionsList);
		}
		if (questionsList.size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}
		model.addAttribute("question", questionDao.putInQuestion(questionsList.get(0)).getQuestionText());
		return "quize";
	}

	@PostMapping("/quize/check")
	public String checkQuize(@RequestParam("userAnswer") String userAnswer, HttpSession session, Model model,
			@SessionAttribute(name = "questions", required = false) List<Integer> questionsList) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		if (questionsList == null) {
			return "redirect:/quize";
		}
		if (questionsList.size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}
		boolean isCorrect;
		model.addAttribute("userAnswer", userAnswer);
		model.addAttribute("questions", questionsList);
		model.addAttribute("answer", questionDao.putInQuestion(questionsList.get(0)).getAnswerText());
		if (userAnswer.equals(questionDao.putInQuestion(questionsList.get(0)).getAnswerText())) {
			isCorrect = true;
		} else {
			isCorrect = false;
		}
		model.addAttribute("isCorrect", isCorrect);

		return "result";

	}

	@GetMapping("/onemore")
	public String oneMoreQuize(HttpSession session, Model model,
			@SessionAttribute(name = "questions", required = false) List<Integer> questionsList) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		if (questionsList == null) {
			return "redirect:/quize";
		}
		if (questionsList.size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}
		questionsList.remove(0);
		model.addAttribute("question", questionDao.putInQuestion(questionsList.get(0)).getQuestionText());
		return "quize";
	}

	@GetMapping("/tomenu")
	public String toMenu(HttpSession session,
			@SessionAttribute(name = "questions", required = false) List<Integer> questionsList) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		if (questionsList != null) {
			session.removeAttribute("questions");
		}
		return "redirect:/";
	}

}
