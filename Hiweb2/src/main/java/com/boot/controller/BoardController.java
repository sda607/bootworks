package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.domain.Board;
import com.boot.service.BoardService;





@RequestMapping("/board/")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	//게시글 목록
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board/getBoardList"; 
	}
	
	//게시글 상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = boardService.getBoard(seq);
		model.addAttribute("board", board);
		
		return "board/getBoard";
	}
}
