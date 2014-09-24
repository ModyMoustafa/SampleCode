package com.siliconarabia.okarabiacomments.web.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.siliconarabia.okarabiacomments.dto.UserDTO;

/**
 * Holds the functionality of authenticating users.
 * 
 * @author Ahmed Yehia
 * **/
@SuppressWarnings("serial")
public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

	private final UserDTO user;
	private static final long serialVersionUID = 1L;

	public AuthenticationTokenImpl(UserDTO user,
			Collection<GrantedAuthority> authorities) {

		super(authorities);
		this.user = user;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return user;
	}

}