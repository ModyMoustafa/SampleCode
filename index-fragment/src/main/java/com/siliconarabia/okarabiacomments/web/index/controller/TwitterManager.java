package com.siliconarabia.okarabiacomments.web.index.controller;

import javax.annotation.PostConstruct;

import org.scribe.extractors.BaseStringExtractorImpl;
import org.scribe.extractors.HeaderExtractorImpl;
import org.scribe.extractors.TokenExtractorImpl;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.oauth.OAuthService;
import org.scribe.services.HMACSha1SignatureService;
import org.scribe.services.TimestampServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.TwitterTemplate;
import org.springframework.stereotype.Component;

import com.pearlox.framework.settings.ApplicationSettings;

@SuppressWarnings("restriction")
@Component
public class TwitterManager {
	@Autowired
	private ApplicationSettings applicationSettings;
	// private final String fb_oath_uri =
	// GetValueFormProp("twitter.fb_oath_uri");
	private String apiSecret; // = GetValueFormProp("twitter.secret");
	private String apiKey;// = GetValueFormProp("twitter.appId");
	private String callbackUrl;// =GetValueFormProp("twitter.redirectURI");

	@PostConstruct
	public void setVariables() {
		// fb_oath_uri = GetValueFormProp("twitter.fb_oath_uri");
		apiSecret = applicationSettings.getSetting("twitter.consumerSecret");
		apiKey = applicationSettings.getSetting("twitter.consumerKey");
		callbackUrl = applicationSettings.getSetting("twitter.callback");
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public OAuthService getOAuthService() {
		OAuthConfig config = new OAuthConfig();
		config.setRequestTokenEndpoint("https://api.twitter.com/oauth/request_token");
		config.setAccessTokenEndpoint("https://api.twitter.com/oauth/access_token");
		config.setAccessTokenVerb(Verb.POST);
		config.setRequestTokenVerb(Verb.POST);
		config.setApiKey(apiKey);
		config.setApiSecret(apiSecret);
		config.setCallback(callbackUrl);

		return new OAuth10aServiceImpl(new HMACSha1SignatureService(),
				new TimestampServiceImpl(), new BaseStringExtractorImpl(),
				new HeaderExtractorImpl(), new TokenExtractorImpl(),
				new TokenExtractorImpl(), config);
	}

	public String getAuthorizeUrl() {
		return "https://api.twitter.com/oauth/authorize";
	}

	public TwitterTemplate getTemplate(Token token) {

		if (token != null) {
			return new TwitterTemplate(apiKey, apiSecret, token.getToken(),
					token.getSecret());
		} else {
			return null;
		}

	}

}