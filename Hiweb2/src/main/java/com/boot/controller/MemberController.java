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
	
	//회원가입 처리
	@PostMapping("/signup")
	public String signup(Member member, Model  model) {
		memberService.signup(member);
		model.addAttribute("msg", "가입");
		return "member/result";
	}
	//회원정보
	@GetMapping("/view")
	public String view(String userid, Model  model) {
		Member member = memberService.view(userid);
		model.addAttribute("member", member);
		return "member/view";
	}
	
	//회원정보 수정
	@PostMapping("/update")
	public String update(Member member, Model model) {
		memberService.update(member);
		model.addAttribute("msg", "수정");
		return "member/result";
		
	}
	
	//회원정보 삭제
	@GetMapping("/delete")
	public String delete(Member member, Model model) {
		memberService.delete(member);
		model.addAttribute("msg", "삭제");
		return "member/result";
	}
	
}
