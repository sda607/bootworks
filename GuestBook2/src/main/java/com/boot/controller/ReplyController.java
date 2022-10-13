package com.boot.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.ReplyDto;
import com.boot.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/replies/")
@RestController
public class ReplyController {

	private final ReplyService replyService;
	
	@GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDto>> getListByBoard(
			@PathVariable("bno") Long bno){
		return new ResponseEntity<>()
	}
	
}
