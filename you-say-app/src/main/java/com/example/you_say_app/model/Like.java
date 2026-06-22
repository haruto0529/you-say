package com.example.you_say_app.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.you_say_app.model.dao.LikesDao;
import com.example.you_say_app.model.dto.LikeDto;

public class Like {

	@Autowired
	private LikesDao likesDao;

	//	いいね押されたときに、まだ一度もいいねしてなかったらインサートする
	//	すでにいいねしていたら、取り消しする
	//	取り消しの状態から、いいねをしたらいいね状態になるメソッド
	public int like(int collectionId) {
		int ret = 0;
		LikeDto likeDto = likesDao.getRecord(collectionId);

		if (likeDto == null) {
			ret = likesDao.setLike(collectionId);
		} else {
			if (likeDto.getDeletedAt() != null) {
				ret = likesDao.upDateNull(collectionId);
			} else {
				ret = likesDao.upDateTime(collectionId);
			}
		}
		return ret;
	}

	//	自分がいいねした名言を持ってくる
	//	名言ごとのいいねを持ってくる

}
