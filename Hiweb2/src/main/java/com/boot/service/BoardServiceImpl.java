package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepo;
	//목록 보기
	@Override
	public List<Board> getBoardList() {
		return boardRepo.findAll();		//오름 차순 정렬
		//return boardRepo.findAll(Sort.Direction.DESC, "seq"));	
	}
	
	//글 상세
	@Override
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	//글쓰기
	@Override
	public void insertBoard(Board board) {
	
		boardRepo.save(board);
	}
	//조회수
	@Transactional
	@Override
	public void updateCount(Long seq) {
		boardRepo.updateCount(seq);
	}
	//글 수정
	@Override
	public void updateBoard(Board board) {
		boardRepo.save(board);
	}
	//글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

	
	
}
