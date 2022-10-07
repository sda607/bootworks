package com.boot.service;

import org.springframework.stereotype.Service;

import com.boot.dto.GuestbookDto;
import com.boot.entity.Guestbook;
import com.boot.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class GuestbookServiceImpl implements GuestbookService{

	
	private final GuestbookRepository repository;
	
	@Override
	public Long register(GuestbookDto dto) {
		log.info("DTO");
		log.info(dto);
		
		
		Guestbook entity = dtoToEntity(dto);
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getGno();
	}

}
