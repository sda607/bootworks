package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {

	void signup(Member memeber);	//회원가입
	
	Member view(String userid);		//상세보기
}
