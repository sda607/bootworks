package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {

	@GetMapping("/")
	public String index() {
		
		log.info("indext 요청입니다.");
		return "index";
	} 
	
	@GetMapping("/member")
	public void forMember() {
		log.info("member요청 입니다.");
	}
}
