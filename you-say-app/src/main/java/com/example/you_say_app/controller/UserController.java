package com.example.you_say_app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.you_say_app.model.Login;
import com.example.you_say_app.model.dao.UserDao;
import com.example.you_say_app.model.dto.UserDto;

@Controller
public class UserController {
	@Autowired
	private UserDao userdao;

	@PostMapping("/login")
	public String doLogin(
			@RequestParam("mail") String mail,
			@RequestParam("password") String password,
			HttpSession session,
			Model model) {

		if (session.getAttribute("loginUser") != null) {
			return "redirect:/";
		}

		if (mail == null || password == null || mail.isEmpty() || password.isEmpty()) {
			return "redirect:/top";
		}

		UserDto userdto = userdao.verify(mail);

		if (userdto == null) {
			model.addAttribute("err", "メールアドレスまたはパスワードが違います");
			model.addAttribute("email", mail);
			return "top";
		}

		Login login = new Login(userdto);

		if (!login.checkPassword(password)) {
			model.addAttribute("err", "メールアドレスまたはパスワードが違います");
			model.addAttribute("email", login.getUserDto().getMail());
			return "top";
		}

		session.setAttribute("loginUser", login.getUserDto()); // セッションに保存

		return "redirect:/";
	}

	@PostMapping("/logout")
	public String logOut(HttpSession session) {
		if (session.getAttribute("loginUser") != null) {
			session.invalidate();
		}
		return "redirect:/top";
	}
	
	@PostMapping("/registration/new")
	public String register(
			@RequestParam("username") String name,
			@RequestParam("mail") String mail,
			@RequestParam("password") String password,
			HttpSession session) {
		
		session.setAttribute("loginUser", userdao.userRegister(name, mail, password));
		return "redirect:/";
		
	}

}
