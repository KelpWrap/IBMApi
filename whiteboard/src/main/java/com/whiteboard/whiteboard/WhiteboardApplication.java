package com.whiteboard.whiteboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WhiteboardApplication {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {

		SpringApplication.run(WhiteboardApplication.class, args);
	}

}
