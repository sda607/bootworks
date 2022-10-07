package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainConetroller {

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
}
