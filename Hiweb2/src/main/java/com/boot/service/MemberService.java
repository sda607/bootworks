package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {

	void signup(Member member);	//회원가입
	
	Member view(String userid);	//상세보기
	
	void update(Member member); //회원정보 수정
	
	void delete(Member member);	//회원정보 삭제
}
