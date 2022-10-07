package com.boot.Repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Guestbook;
import com.boot.repository.GuestbookRepository;

@SpringBootTest
public class GuestbookRepositorytests {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	//데이터 저장 300개
	@Test
	public void insertDate() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Guestbook guestbook = Guestbook.builder()
					.title("Title....." + i)
					.content("content" + i)
					.writer("user....." + (i % 10))
					.build();
			
			System.out.println(guestbookRepository.save(guestbook));
			
		});
		
		
	}
}
