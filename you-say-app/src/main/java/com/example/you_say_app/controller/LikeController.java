package com.example.you_say_app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.you_say_app.model.Like;

@Controller
public class LikeController {

	@Autowired
	private Like like;
	
	

	//	いいねマークを押したときの処理
	@PostMapping("/like")
	@ResponseBody
	public int putLike(@RequestBody Map<String, Integer> map) {
		like.like(map.get("collectionId"));

		return like.likeCount(map.get("quoteId"));
	}

}
