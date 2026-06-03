package com.example.you_say_app.model.dto;

public class UserDto {
	private String userId;
	private String name;
	private String mail;
	private String password;
	private String salt;
	private String rankId;

	public UserDto() {

	}

	public UserDto(String userId, String name, String mail, String password, String salt, String rankId) {
		super();
		this.userId = userId;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.salt = salt;
		this.rankId = rankId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRankId() {
		return rankId;
	}

	public void setRankId(String rankId) {
		this.rankId = rankId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
