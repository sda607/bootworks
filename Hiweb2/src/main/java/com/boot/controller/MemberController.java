package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.domain.Member;
import com.boot.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인
	@GetMapping("/login")
	public void login() {}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout() {
		
		return "redirect:/";
	}
	
	//회원가입 폼 요총
	@GetMapping("/signup")
	public void signup() {}
	
	
	@PostMapping("/signup")
	public String signup(Member member) {
		memberService.signup(member);
		return "redirect:login";
	}
	//회원정보
	@GetMapping("/view")
	public String view(String userid, Model  model) {
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/view";
	}
}
