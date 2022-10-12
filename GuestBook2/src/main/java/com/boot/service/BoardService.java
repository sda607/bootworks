package com.boot.service;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;

public interface BoardService {

	//게시글 등록
	Long register(BoardDto dto);
	
	//게시글 목록 보기 //Object[]
	PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto);
	
	
	
	//dto에서 Entity로 변환
	default Board dtoToEntity(BoardDto dto) {
		//회원 생성
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		
		//게시글 작성
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return board;
	}
	
}
