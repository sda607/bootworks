package com.boot.dto;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardDto {

	private Long boardNum;	//게시글 번호
	private String title;	//제목
	private String content;	//내용
	private Member member;	//작성자
}
