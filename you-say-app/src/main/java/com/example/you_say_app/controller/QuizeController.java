package com.example.you_say_app.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.you_say_app.model.Question;
import com.example.you_say_app.model.dao.QuestionDao;
import com.example.you_say_app.model.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizeController {
	@Autowired
	private QuestionDao questiondao;

	@GetMapping("/quize")
	public String quize(HttpSession session, Model model) {
		UserDto userdto = (UserDto) session.getAttribute("loginUser");
		if (userdto == null) {
			return "redirect:/top";
		}
		Question question = (Question) session.getAttribute("questions");
		if (question == null) {
			question = questiondao.putInQuestion();
			Collections.shuffle(question.getQuestion());
			session.setAttribute("questions", question);
		}
		if (question.getQuestion().size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}
		model.addAttribute("question", question.getQuestion());
		return "quize";
	}

	
	@PostMapping("/quize/check")
	public String checkQuize(@RequestParam("userAnswer") String userAnswer, HttpSession session, Model model) {
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		if (userDto == null) {
			return "redirect:/top";
		}
		Question question = (Question) session.getAttribute("questions");
		if (question == null) {
			return "redirect:/quize";
		}
		if (question.getQuestion().size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}
		boolean isCorrect;
		model.addAttribute("userAnswer", userAnswer);
		model.addAttribute("answer", question.getQuestion().get(0).getAnswerText());
		if (userAnswer.equals(question.getQuestion().get(0).getAnswerText())) {
			isCorrect = true;
		} else {
			isCorrect = false;
		}
		model.addAttribute("isCorrect", isCorrect);
		model.addAttribute("question", question);

		return "result";

	}

	@GetMapping("/onemore")
	public String oneMoreQuize(HttpSession session, Model model) {
		UserDto userdto = (UserDto) session.getAttribute("loginUser");
		if (userdto == null) {
			return "redirect:/top";
		}
		Question question = (Question) session.getAttribute("questions");
		if (question == null) {
			return "redirect:/quize";
		}
		if (question.getQuestion().size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}
		question.deleteTopQuestion();
		model.addAttribute("question", question.getQuestion());
		return "quize";
	}

	@GetMapping("/tomenu")
	public String toMenu(HttpSession session) {
		UserDto userdto = (UserDto) session.getAttribute("loginUser");
		if (userdto == null) {
			return "redirect:/top";
		}
		Question question = (Question) session.getAttribute("questions");
		if (question != null) {
			session.removeAttribute("questions");
		}
		return "redirect:/";
	}

}
