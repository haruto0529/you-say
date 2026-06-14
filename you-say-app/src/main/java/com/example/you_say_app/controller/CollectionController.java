package com.example.you_say_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollectionController {
	@GetMapping("/collection")
	public String collection() {
		
		return"collection";
	}

}
