package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(properties= {
								"author.name=test",
								"author.age=50"
		
								})
public class PropertiesTests {

	@Autowired
	Environment environment;
	
	@Test
	public void testMethod() {
		System.out.println("이름" + environment.getProperty("author.name"));
		System.out.println("나이" + environment.getProperty("author.age"));
	}
	
}
