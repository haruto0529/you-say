package com.example.you_say_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

	    @GetMapping("/")
	    public String top() {
	        return "top";
	    }

}
