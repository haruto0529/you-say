package com.example.you_say_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class YouSayAppApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("MY_DB_URL", dotenv.get("MY_DB_URL"));
		System.setProperty("MY_DB_USER", dotenv.get("MY_DB_USER"));
		System.setProperty("MY_DB_PASS", dotenv.get("MY_DB_PASS"));

		SpringApplication.run(YouSayAppApplication.class, args);
	}

}
