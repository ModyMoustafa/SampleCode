package com.siliconarabia.okarabiacomments.web.index.controller;

import org.scribe.model.Token;

/**
 * 
 * @author Michael
 * 
 */
public interface ILinkedinManager {
	public Token retrieveAccessToken(Token requestToken, String verifier);

	public String getLinkedintAuthenticationUrl(String token);

	public Token retrieveRequestToken();

    public String getApiKey();

    public String getApiSecret();
}
