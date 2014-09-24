package com.siliconarabia.okarabiacomments.web.index.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pearlox.framework.settings.ApplicationSettings;

/**
 * @author gasser
 */
@Component
public class FaceBookManager implements IFaceBookManager {
	private static final Logger log = LoggerFactory
			.getLogger(FaceBookManager.class);
	@Autowired
	private ApplicationSettings applicationSettings;
	private final String fb_oath_uri = "https://graph.facebook.com/oauth/";
	private final String fb_oath_uri_dialog = "https://www.facebook.com/dialog/oauth";
	// private final String fb_oath_uri =
	// GetValueFormProp("facebook.fb_oath_uri");
	private String secret; // = GetValueFormProp("facebook.secret");
	private String clientId;// = GetValueFormProp("facebook.appId");
	private String redirectURI;// =GetValueFormProp("facebook.redirectURI");

	@PostConstruct
	public void setVariables() {
		// fb_oath_uri = GetValueFormProp("facebook.fb_oath_uri");
		secret = GetValueFormProp("facebook.secret.key");
		clientId = GetValueFormProp("facebook.application.id");
		redirectURI = GetValueFormProp("app.url.online") + "/facebook";
	}

	private String assembleAuthentication(String authCode) {
		return fb_oath_uri + "access_token?" + "client_id=" + clientId
				+ "&redirect_uri=" + redirectURI + "&client_secret=" + secret
				+ "&code=" + authCode;
	}

	@Override
	public String retrieveAccessToken(String code) {
		String accessToken = null;
		if (null != code) {
			String authURL = assembleAuthentication(code);
			URL uri = null;
			try {
				uri = new URL(authURL);
				String result = crawlToURL(uri);
				String[] values = result.split("&");
				for (String value : values) {
					String[] valuePair = value.split("=");
					if (valuePair.length != 2) {
						throw new RuntimeException("Unexpected auth response");
					} else {
						if (valuePair[0].equals("access_token")) {
							accessToken = valuePair[1];
						}
					}
				}
			} catch (RuntimeException runTimeException) {
				log.error(runTimeException.getMessage());
				return null;
			} catch (MalformedURLException e) {
				log.error(e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return accessToken;
	}

	private String crawlToURL(URL uri) {
		ByteArrayOutputStream byteOutputStream = null;
		InputStream inputStream = null;
		String stream = null;
		try {
			inputStream = uri.openStream();
			byteOutputStream = new ByteArrayOutputStream();
			int i;
			while ((i = inputStream.read()) != -1) {
				byteOutputStream.write(i);
			}
			stream = new String(byteOutputStream.toByteArray());
			byteOutputStream.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (null != byteOutputStream) {
				try {
					byteOutputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return stream;
	}

	@Override
	public String getFacebookAuthenticationUrl() {
		return fb_oath_uri_dialog + "authorize?" + "client_id="
				+ clientId + "&display=page&redirect_uri=" + redirectURI
				+ "&scope=email";
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}

	private String GetValueFormProp(String key) {
		return applicationSettings.getSetting(key);
	}
}
