package com.siliconarabia.okarabiacomments.web.index.controller;

import org.scribe.oauth.OAuthService;

public interface ITwitterManager {

	public String retrieveAccessToken(String code);

	public String fbLoginAuthenticate();

	public OAuthService getOAuthService();

}
