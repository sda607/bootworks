package com.boot.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.ReplyDto;
import com.boot.service.ReplyService;

@SpringBootTest
public class ReplyServiceTests {

	@Autowired
	private ReplyService service;
	
	
	@Test
	public void testGetList() {
		Long bno= 99L;
		List<ReplyDto> dtoList = service.getList(bno);
		
	}
	
}
