package com.boot.service;

import org.springframework.stereotype.Service;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepo;
	//게시글 등록
	@Override
	public Long register(BoardDto dto) {
		Board board = dtoToEntity(dto);
		boardRepo.save(board);
		
		return board.getBno();
	}
	//게시글 목록 보기
	@Override
	public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
