package com.boot.Repository;

import java.util.Optional;
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
	
	@Test
	public void updateTest() {
		Optional<Guestbook> result = guestbookRepository.findById(300L);
		
		if(result.isPresent()) {
			Guestbook guestbook = result.get();
			
			guestbook.changeTitle("제목수정....");
			guestbook.changeContent("내용수정....");
			
			guestbookRepository.save(guestbook);
		}
	}
	
	
}
