package com.springboot.security.web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.security.domain.user.User;
import com.springboot.security.domain.user.UserRepository;
import com.springboot.security.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 붙은 상태인 객체에 모두 @Autowired를 달아준다
@Service
public class AuthServiceImpl implements AuthService{
   
	private final UserRepository userRepository;
	
	@Override
	public int signup(SignupDto signupDto) {
		User user = signupDto.toEntity();
		int usernameCheckResult = userRepository.usernameCheck(user);
		if (usernameCheckResult == 1) {
			// 이미 존재하는 username
			return 2;
		} else {
			// 회원가입 가능
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // 비밀번호를 암호화 한다
			user.setRole("ROLE_USER");
			int signupResult = userRepository.signup(user);
			return signupResult;
		}
	}
}




















