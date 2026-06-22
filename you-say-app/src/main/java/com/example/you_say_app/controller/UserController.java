package com.example.you_say_app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.you_say_app.model.Profile;
import com.example.you_say_app.model.dao.UserDao;
import com.example.you_say_app.model.dto.UserDto;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;

	@Autowired
	private Profile profile;

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

		UserDto userDto = userDao.verify(mail);

		if (userDto == null) {
			model.addAttribute("err", "メールアドレスまたはパスワードが違います");
			model.addAttribute("email", mail);
			return "top";
		}

		if (!password.equals(userDto.getPassword())) {
			model.addAttribute("err", "メールアドレスまたはパスワードが違います");
			model.addAttribute("email", userDto.getMail());
			return "top";
		}

		session.setAttribute("loginUser", userDto.getUserId()); // セッションに保存

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
			HttpSession session,
			RedirectAttributes redirectAttrs) {

		if (userDao.isMailDuplication(mail)) {
			redirectAttrs.addFlashAttribute("errorMassage", "すでに登録済みです");
			return "redirect:/registration";
		}
		if (userDao.isNameDuplication(name)) {
			redirectAttrs.addFlashAttribute("errorMassage", "すでに登録済みです");
			return "redirect:/registration";
		}
		userDao.userRegister(name, mail, password);
		UserDto user = userDao.verify(mail);
		int userId = user.getUserId();
		session.setAttribute("loginUser", userId);
		return "redirect:/";

	}
	

	//	プロフィール編集をするコントローラー
	@PostMapping("/profile/update")
	public String changeProfile(@RequestParam(name = "username") String userName,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "currentPassword", required = false) String currentPassword, HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/";
		}
		if (profile.changeProfile((int) session.getAttribute("loginUser"), userName, currentPassword, password)) {
			redirectAttributes.addFlashAttribute("message", "ユーザー情報を更新しました！");
		} else {
			redirectAttributes.addFlashAttribute("message", "パスワードが違います");
		}

		return "redirect:/";

	}

	@PostMapping("/profile/delete")
	public String delete(HttpSession session, @RequestParam("currentPassword") String currentPassword,
			RedirectAttributes redirectAttributes) {
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/";
		}

		int userId = (int) session.getAttribute("loginUser");

		if (userDao.getUserInfo(userId).getPassword().equals(currentPassword)) {
			userDao.unsubscribe(userId);
			session.invalidate();
		} else {			
			redirectAttributes.addFlashAttribute("message", "パスワードが違います");
		}
		
		return "redirect:/";


	}

}
}
