package com.example.you_say_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.you_say_app.model.dao.UserDao;
import com.example.you_say_app.model.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class RoutingController {

	@Autowired
	private UserDao userdao;

	@GetMapping("/")
	public String top(HttpSession session, Model model) {
		UserDto loginUser = (UserDto) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/top";
		}
		model.addAttribute("user_name", loginUser.getName());
		model.addAttribute("user_rank", userdao.userRank(loginUser.getRankId()));
		return "index";
	}

	@GetMapping("/top")
	public String top() {
		return "top";
	}

}
