package com.tesdb.domain;

import org.springframework.security.core.GrantedAuthority;

public class UserGrant implements GrantedAuthority {

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return "ADMIN";
	}

}
