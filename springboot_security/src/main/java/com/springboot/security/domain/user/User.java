package com.springboot.security.domain.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private String id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String role;
	private Date creatdate;
	private Date updatedate;
}
















