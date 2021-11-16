package com.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration // ioc 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // csrf 비활성화 csrf:토큰을 자동으로 심어줌
		http.authorizeRequests()
			.antMatchers("/","/index", "/user/**")
			.authenticated() // 인증이 필요합니다
			.anyRequest() // antMatchers 외에 모든요청
			.permitAll() // 요청이 없어도 된다(모든 권한을 부여한다)
			.and()
			.formLogin() // 로그인 페이지 커스텀
			.loginPage("/auth/signin") // getmapping 컨트롤러 만들어줘야한다
			.loginProcessingUrl("/auth/signin") //post controller를 따로 만들필요 없음(security가 자동으로 처리)
			.defaultSuccessUrl("/"); // 로그인을 하면 여기로 이동(index 페이지)
			
	}

}
