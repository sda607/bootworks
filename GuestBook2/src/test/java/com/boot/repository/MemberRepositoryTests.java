package com.boot.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Member;

@SpringBootTest
public class MemberRepositoryTests {

	@Autowired
	MemberRepository memberRepo;
	
	@Test
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			Member member = Member.builder()
					.email("user" + i + "@aaa.com")
					.password("1234")
					.name("user" + i)
					.build();
			
			memberRepo.save(member);
		});
		
	}
	
}
