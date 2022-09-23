package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.domain.Board;
import com.boot.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "getBoardList"; //getBoardList.jsp
	}
	
	//새글등록 폼 페이지 요청
	@GetMapping("/insertBoard")
	public String insertBoard() {
		log.info("insertBoard 페이지 진입");
		return "insertBoard";	//board/insertBoard.jsp
	}
	
	//새글 등록 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		log.info("insertBoard 진행 완료");
		return "redirect:getBoardList";
	}
	
	//글 상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCount(seq);	//조회수 증가
		Board board = service.getBoard(seq);
		
		
		model.addAttribute("board", board);	//model - "board" 보냄
		return "getBoard";
	}
	
	//글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		
		service.updateBoard(board);
		return "redirect:getBoardList";
	}

	
}
