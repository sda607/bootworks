package com.shop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

	private final MemberService memberService;
	
	private final PasswordEncoder pwencoder;
	
	//로그인 폼 요청
	@GetMapping("/login")
	public String loginForm() {
		return "member/loginForm";
	}
	
	//로그인 실패
	@GetMapping("/login/error")
	public String loginFormError(Model model) {
		model.addAttribute("loginErrorMsg", "다시접근");
		return "member/loginForm";
	}
	
	//회원 가입 폼 요청
	@GetMapping("/new")
	public String memberForma(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	//회원 가입 처리
	@PostMapping("/new")
	public String memberForm(MemberFormDto memberformDto,
			BindingResult bindingResult, Model model) {
		//유효성 검증
		if(bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		//이메일 중복 처리
		try {
			Member member = Member.createMember(memberformDto, pwencoder);
			memberService.saveMember(member);
		}catch(IllegalStateException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "member/memberForm";
		}
		return "redirect:/";
		
		
		
	}
	
	
}