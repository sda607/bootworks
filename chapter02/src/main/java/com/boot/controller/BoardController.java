package com.boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.domain.BoardVO;
import com.boot.service.BoardService;

@RestController
public class BoardController {
	//service 객체 생성
	@Autowired
	private BoardService service;
	
	//문자열 리턴, 파라미터에 값 설정
	@GetMapping("/hello")
	public String hello(String name) {
		return service.hello(name);
	}
	
	//VO 객체 리턴
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = service.getBoard();
		return board;
	}
	
	//컬렉션(리스트) 리턴
	@GetMapping("/getBoardList")
	public List<BoardVO> getboardList(){
		List<BoardVO> boardList = service.getboardList();
		
		return boardList;
	}
	
	
}
