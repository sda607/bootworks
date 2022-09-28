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
	
	@GetMapping("/manager")
	public void forManager() {
		log.info("manager 요청 입니다.");
	}
	
	@GetMapping("/admin")
	public void forAdmin() {
		log.info("Admin 요청 입니다.");
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("login 요청입니다");
	}
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		log.info("loginSuccess성공");
	}
	@GetMapping("/accessDenied")
	public void accessDenied() {
		log.info("accessDenied 접근 거부");
	}
}
