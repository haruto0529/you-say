package com.example.you_say_app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.you_say_app.model.dao.UserDao;

@Controller
public class RoutingController {

	@Autowired
	private UserDao userdao;

	@GetMapping("/")
	public String showIndex(HttpSession session, Model model) {
		Object loginUser = session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/top";
		}
		model.addAttribute("user_name", userdao.getUserInfo((int) session.getAttribute("loginUser")).getName());
		model.addAttribute("user_rank", userdao.userRank((int) session.getAttribute("loginUser")));
		return "index";
	}

	@GetMapping("/registration")
	public String registerPage() {
		return "registration";
	}

	@GetMapping("/top")
	public String showTop() {
		return "top";
	}
	
	
	@GetMapping("/minigame")
    public String showStart() {
        return "connect4";
    }

}
