//package com.example.you_say_app.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.example.you_say_app.model.Like;
//
//@Controller
//public class LikeController {
//
//	@Autowired
//	private Like like;
//
//	//	いいねマークを押したときの処理
//	@PostMapping("/like")
//	@ResponseBody
//	public Map<String, Integer> putLike(int collectionId) {
//		Map<String, Object> count = new HashMap<>();
//		like.like(collectionId);
//
////		count.put("like", like.countLike(collectionId));
//
//	}
//
//}
