package com.shop.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional getCurrentAuditor() {
		//현재 로그인 한 사용자의 정보를 조회하여 사용자의 이름을 등록자와 수정자로 지정함
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		String userId = "";
		if(authentication != null) {
			userId = authentication.getName();
		}
		return Optional.of(userId);
	}

	
}
