package com.boot.dto;

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

	private Long bno;
	private String title, content, writerEmail, writerName;
	private LocalDateTime regDate, modDate;
	private int replyCount;					//해당 게시글의 댓글 수
}
