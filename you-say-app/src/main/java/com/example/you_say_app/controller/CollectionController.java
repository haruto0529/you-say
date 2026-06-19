package com.example.you_say_app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.you_say_app.model.dto.UserDto;

@Controller
public class CollectionController {
	@GetMapping("/collection")
	public String showCollection(HttpSession session) {
		UserDto loginUser = (UserDto) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/top";
		}
		return "collection";
	}

}
