package com.example.you_say_app.model;

import com.example.you_say_app.model.dto.UserDto;

public class Login {
	private UserDto userDto;

	public Login(UserDto userDto) {
		this.userDto = userDto;
	}


	public UserDto getUserDto() {
		return this.userDto;
	}

	public boolean checkPassword(String sentPassword) {

		if (this.userDto.getPassword().equals(sentPassword)) {
			return true;
		} else {
			return false;
		}
	}

}
