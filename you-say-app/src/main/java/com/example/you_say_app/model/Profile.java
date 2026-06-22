package com.example.you_say_app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.you_say_app.model.dao.UserDao;
import com.example.you_say_app.model.dto.UserDto;

@Service
public class Profile {
	@Autowired
	private UserDao userDao;

	//	プロフィールを変更するメソッド
	public boolean changeProfile(int userId, String userName, String currentPassword, String password) {

		UserDto userDto = userDao.getUserInfo(userId);

		boolean isUpdate = true;

		if (userDto.getName().equals(userName)) {
			if (userDto.getPassword().equals(currentPassword)) {
				userDao.updatePassword(userId, password);
			} else {
				isUpdate = false;
			}
		} else {
			if (currentPassword == null || currentPassword.isEmpty()) {
				userDao.updateName(userId, userName);
			} else if (userDto.getPassword().equals(currentPassword)) {
				userDao.updateNamePassword(userId, userName, password);
			} else {
				isUpdate = false;
			}
		}
		return isUpdate;

	}

}
