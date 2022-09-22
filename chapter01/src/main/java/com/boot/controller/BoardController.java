package com.boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.domain.BoardVO;

@RestController
public class BoardController {

	public BoardController() {
		System.out.println("==> BoardController생성");
	}
	//문자열 리턴
	@GetMapping("/hello")
	public String hello() {
		return "안녕 Hello! springboot";
	}
	
	//문자열 리턴, 파라미터에 값 설정
	@GetMapping("/hello2")
	public String hello2(String name) {
		return "안녕~~~" + name;
	}
	
	//VO 객체 리턴
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목");
		board.setWriter("글쓴이");
		board.setContent("테스트 내용입니다.");
		board.setCreateDate(new Date());
		board.setCnt(0);
		
		return board;
	}
	
	//컬렉션(리스트) 리턴
	@GetMapping("/getBoardList")
	public List<BoardVO> getboardList(){
		List<BoardVO> boardList = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("테스트 제목");
			board.setWriter("글쓴이");
			board.setContent(i + "테스트 내용입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0);
			
			boardList.add(board);
		}
		return boardList;
	}
	
	
}
