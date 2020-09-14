package com.rebell.alliance.intelligence;

import static org.assertj.core.api.Assertions.assertThat;

import com.rebell.alliance.intelligence.controller.CommunicationController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunicationApplicationTests {

	@Autowired
	private CommunicationController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
