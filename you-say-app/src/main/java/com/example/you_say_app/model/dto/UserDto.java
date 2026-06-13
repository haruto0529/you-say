package com.example.you_say_app.model.dto;

public class UserDto {
	private int userId;
	private int rankId;
	private String name;
	private String mail;
	private String password;
	private String salt;

	public UserDto() {

	}

	public UserDto(int userId, int rankId, String name, String mail, String password, String salt) {
		super();
		this.userId = userId;
		this.rankId = rankId;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.salt = salt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
