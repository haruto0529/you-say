package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SuperDao {

	@Value("${my.db.url}")
	private String dbUrl;

	@Value("${my.db.user}")
	private String dbUser;

	@Value("${my.db.pass}")
	private String dbPass;

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, dbUser, dbPass);
	}

}
