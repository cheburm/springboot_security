package com.springboot.security.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.security.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	@Override // 권한이 하나가 아닐수 있기 때문에 리턴값이 Collection(coqja 이라는 아이디가 ROLE_ADMIN, ROLE_USER 를 가질수있다.)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		
		return collection;
	}

	@Override //
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; // 계정이 만료됬는지 묻는다
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true; // 계정이 잠겼는지 묻는다
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true; // 1년이상 계정을 사용하지 않으면 휴면계정
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true; // 계정을 임시탈퇴
	}

}
