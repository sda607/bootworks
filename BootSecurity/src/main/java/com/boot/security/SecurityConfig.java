package com.boot.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//시큐리티 설정 파일을 의미하며 시큐리티를 사용하는 데 필요한 수많은 객체를 생성함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		//인증과 권한 설정
		security.authorizeRequests().antMatchers("/").permitAll();
		//로그인 인증 통과
		security.authorizeRequests().antMatchers("/member/**").authenticated();
	}

	
	
}
