package com.siliconarabia.okarabiacomments.web.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Holds the functionality of authenticating facebook users
 * 
 * @author Ahmed Yehia
 * **/
public class FacebookAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean supports(Class<? extends Object> authentication) {
		return AuthenticationTokenImpl.class.isAssignableFrom(authentication);
	}

}