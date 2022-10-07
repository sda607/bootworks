package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.GuestbookDto;


@SpringBootTest
public class GuestbookServiceTests {

	@Autowired
	private GuestbookService service;
	
	//새글 등록
	@Test
	public void testRegister() {
		
		GuestbookDto guestbookDto = GuestbookDto.builder()
				.title("title")
				.content("content")
				.writer("writer")
				.build();
		
		System.out.println(service.register(guestbookDto));
		
	}
}
