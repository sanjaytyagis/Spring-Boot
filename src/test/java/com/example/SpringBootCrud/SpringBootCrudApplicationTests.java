package com.example.SpringBootCrud;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.SpringBootCrudApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCrudApplication.class)
@SpringBootTest
class SpringBootCrudApplicationTests {

	@Test
	void contextLoads() {
	}

}
