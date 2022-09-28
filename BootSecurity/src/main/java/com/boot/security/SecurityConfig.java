package com.boot.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//시큐리티 설정 파일을 의미하며 시큐리티를 사용하는 데 필요한 수많은 객체를 생성함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		//인증과 권한 설정
		/*security.authorizeRequests().antMatchers("/").permitAll();
		//로그인 인증 통과
		security.authorizeRequests().antMatchers("/member/**").authenticated();*/
		
		//
		security.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/member/**").authenticated()		//인증
			.antMatchers("/manager/**").hasRole("MANAGER")	//권한
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		security.csrf().disable();
		//시큐리티 제공 로그인 폼
		//security.formLogin();
		security.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);
		
		//접근 권한 없음 페이지 처리
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//로그아웃 처리
		security.logout().invalidateHttpSession(true)
				.logoutSuccessUrl("/login");
	}
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		//db에 저장된 사용자로 인증 처리
		//query1 : 인증 - 사용자가 입력한 아이디로 사용자 정보 조회
	
		String query1 = "select id username, concat('{noop}', password) password,"
					+ "true enabled from member where id=?";
		//query2 : 사용자가 입력한 아이디로 권한 정보 조회
		String query2 = "select id, role from member where id=?";
		
		//jdbcAuthentication() 사용
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(query1)		//인증
			.authoritiesByUsernameQuery(query2);//권한
		
		
		
		
		
		/*auth.inMemoryAuthentication()
			.withUser("manager")		// 사용자 아이디 설정
			.password("{noop}manager123")// 비밀번호 대한 암호화 하지 않음
			.roles("MANAGER");			// 권한 설정
		auth.inMemoryAuthentication()
		.withUser("admin")		// 사용자 아이디 설정
		.password("{noop}admin123")// 비밀번호 대한 암호화 하지 않음
		.roles("ADMIN");			// 권한 설정*/
	}
	
}
