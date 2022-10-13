package com.boot.service;

import java.util.List;
import java.util.stream.Collector;

import org.springframework.stereotype.Service;

import com.boot.dto.ReplyDto;
import com.boot.entity.Board;
import com.boot.entity.Reply;
import com.boot.repository.replyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{

	private final replyRepository replyRepo;
	
	@Override
	public List<ReplyDto> getList(Long bno) {
		
		Board board = Board.builder().bno(bno).build();
		
		List<Reply> result = replyRepo.getRepliesByBoardOrderByRno(board);
		result = result.stream().map(reply -> entityToDto(reply)).collect(Collector);
		return result;
	}

}
