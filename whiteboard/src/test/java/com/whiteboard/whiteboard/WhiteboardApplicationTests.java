package com.whiteboard.whiteboard;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.whiteboard.whiteboard.API.controllers.CatalogPostController;
import com.whiteboard.whiteboard.API.controllers.CatalogUserController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class WhiteboardApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkBeansInApplicationContext(){
		assertNotNull(applicationContext.getBean(CatalogPostController.class));
		assertNotNull(applicationContext.getBean(CatalogUserController.class));
	}

}
