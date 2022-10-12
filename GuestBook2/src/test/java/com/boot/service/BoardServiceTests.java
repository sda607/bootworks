package com.boot.service;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDto;


@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() {
		BoardDto dto = BoardDto.builder()
				.title("한글 테스트")
				.content("테스트 내용")
				.writerEmail("user50@aaa.com")
				.build();
		boardService.register(dto);
	}
	
	//Entity에서 dto로 변환
	default BoardDto EntityToDto(Board board, Member member, Long replyCount) {
		
		BoardDto boardDto = BoardDto.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
		
		return boardDto;
	}
	
}
