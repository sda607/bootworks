package com.boot.service;

import java.util.List;

import com.boot.dto.ReplyDto;
import com.boot.entity.Reply;

public interface ReplyService {

	//댓글 목록 보기
	List<ReplyDto> getList(Long bno);
	
	//Reply 객체를 ReplyDto로 반환
	default ReplyDto entityToDto(Reply reply) {
		ReplyDto dto = ReplyDto.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regDate(reply.getModDate())
				.modDate(reply.getModDate())
				.build();
		return dto;
	}
}
