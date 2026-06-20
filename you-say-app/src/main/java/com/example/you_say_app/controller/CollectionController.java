package com.example.you_say_app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.you_say_app.model.dao.CollectionDao;

@Controller
public class CollectionController {
	 @Autowired
	    private CollectionDao collectionDao;
	 
	@GetMapping("/collection")
	//コレクションの全件表示
	public String showCollection(HttpSession session,Model model) {
		Object loginObject = session.getAttribute("loginUser");
		if (loginObject == null) {
			return "redirect:/top";
		}
		int loginUser = (int) loginObject;
		model.addAttribute("collection", collectionDao.collectionDisplay(loginUser));
		return "collection";
	}

}
