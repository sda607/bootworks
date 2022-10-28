package com.boot.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Board;

public interface BoardRepostitory extends JpaRepository<Board, Long>{

	
	
}
