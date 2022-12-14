package com.boot.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	//쿼리 메소드 - 글 제목을 검색
	List<Board> findByTitle(String searchKeyword);
	
	//특정 단어가 포함된 목록 검색(Containing - Like 역할)
	List<Board> findByContentContaining(String searchKeyWord);
	
	//제목 또는 내용에 특정 단어가 포함된 목록 검색
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	//글 제목이 특정 단어가 포함된 글 목록을 내림차순으로 조회
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	//글 제목이 특정 단어가 포함된 글 목록을 내림차순으로 조회(위치기반 파라미터)
	//@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	//List<Board> queryAnnotationTest1(String searchkeyword);
	
	//글 제목이 특정 단어가 포함된 글 목록을 내림차순으로 조회(위치기반 파라미터)
	@Query("SELECT b FROM Board b WHERE b.title LIKE %:searchkeyword% "
			+ "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchkeyword);
}
