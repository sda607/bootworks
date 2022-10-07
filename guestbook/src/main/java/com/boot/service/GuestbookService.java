package com.boot.service;

import com.boot.dto.GuestbookDto;
import com.boot.entity.Guestbook;

public interface GuestbookService {

	//게시글 등록
	Long register(GuestbookDto dto);
	
	
    default Guestbook dtoToEntity(GuestbookDto dto) { Guestbook entity = Guestbook.builder() 
			  .gno(dto.getGno())
			  .title(dto.getTitle())
			  .content(dto.getContent())
			  .writer(dto.getWriter())
			  .build();
    			return entity;
    
	  }
	 
}
