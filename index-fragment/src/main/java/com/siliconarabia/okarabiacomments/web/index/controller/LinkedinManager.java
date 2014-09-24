package com.siliconarabia.okarabiacomments.web.index.controller;

import javax.annotation.PostConstruct;

import org.scribe.extractors.BaseStringExtractorImpl;
import org.scribe.extractors.HeaderExtractorImpl;
import org.scribe.extractors.TokenExtractorImpl;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.services.HMACSha1SignatureService;
import org.scribe.services.TimestampServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pearlox.framework.settings.ApplicationSettings;

/**
 * @author michael
 */
@SuppressWarnings("restriction")
@Component
public class LinkedinManager implements ILinkedinManager {
	@Autowired
	private ApplicationSettings applicationSettings;
	private final String linkedin_oath_uri = "https://api.linkedin.com/uas/oauth/requestToken";
	private final String linkedin_oath_uri_dialog = "https://www.linkedin.com/uas/oauth/";
	private String apiSecret;
	private String apiKey;
	private String callbackUrl;

	@PostConstruct
	public void setVariables() {
		apiSecret = GetValueFormProp("linkedin.api.secret");
		apiKey = GetValueFormProp("linkedin.api.key");
		callbackUrl = GetValueFormProp("app.url.online") + "/linkedin";
	}

	@Override
	public Token retrieveAccessToken(Token requestToken, String verifier) {
		Token accessToken = null;
		if (requestToken != null)
			accessToken = getOAuthService().getAccessToken(requestToken,
					new Verifier(verifier));
		return accessToken;
	}

	private OAuth10aServiceImpl getOAuthService() {
		OAuthConfig config = new OAuthConfig();
		config.setRequestTokenEndpoint(linkedin_oath_uri);
		config.setAccessTokenEndpoint("https://api.linkedin.com/uas/oauth/accessToken");
		config.setAccessTokenVerb(Verb.POST);
		config.setRequestTokenVerb(Verb.POST);
		config.setApiKey(apiKey);
		config.setApiSecret(apiSecret);
		config.setCallback(callbackUrl);

		OAuth10aServiceImpl requestToken = new OAuth10aServiceImpl(
				new HMACSha1SignatureService(), new TimestampServiceImpl(),
				new BaseStringExtractorImpl(), new HeaderExtractorImpl(),
				new TokenExtractorImpl(), new TokenExtractorImpl(), config);

		return requestToken;
	}

	@Override
	public String getLinkedintAuthenticationUrl(String token) {
		return linkedin_oath_uri_dialog + "authorize?"
				+ "oauth_token=" + token;
	}

	public void setSecret(String secret) {
		this.apiSecret = secret;
	}

	public void setClientId(String clientId) {
		this.apiKey = clientId;
	}

	public void setRedirectURI(String redirectURI) {
		this.callbackUrl = redirectURI;
	}

	private String GetValueFormProp(String key) {
		return applicationSettings.getSetting(key);
	}

    public String getApiSecret() {
        return apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
	public Token retrieveRequestToken() {
		return getOAuthService().getRequestToken();
	}
}
