package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Guestbook;


@SpringBootTest
public class GuestbookServiceTests {

	@Autowired
	private GuestbookService service;
	
	//새글 등록
	@Test
	public void testRegister() {
		
		GuestbookDto guestbookDto = GuestbookDto.builder()
				.title("title")
				.content("content")
				.writer("writer")
				.build();
		
		System.out.println(service.register(guestbookDto));
	}
	
	//게시글 목록 보기
	@Test
	public void testList() {
		//페이지 요청
		PageRequestDto pageRequestDto = PageRequestDto.builder()
				.page(1).size(10).build();
		//페이지 겨로가
		PageResultDto<GuestbookDto, Guestbook> resultDto = service.getList(pageRequestDto);
		System.out.println("prev" + resultDto.isPrev());
		System.out.println("next" + resultDto.isNext());
		System.out.println("total" + resultDto.getTotalPage());
		
		//반복처리
		for(GuestbookDto guestbookDto : resultDto.getDtoList()) {
			System.out.println(guestbookDto);
		}
		
		System.out.println("===========================================================");
		resultDto.getPageList().forEach(i -> System.out.print(i + " "));
	}
}
