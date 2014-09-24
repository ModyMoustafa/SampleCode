package com.siliconarabia.okarabiacomments.comment.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;

import com.pearlox.framework.settings.ApplicationSettings;

/**
 * 
 * @author DmitryMa
 * 
 *         Used in test environment to override default application settings
 * 
 */

public class StubMessageSourceSettingsImpl implements ApplicationSettings {

	private MessageSource messageSource;
	private Map<String, String> overridedSettings = new HashMap<String, String>();

	/*
	 * @see
	 * com.pearlox.framework.settings.ApplicationSettings#getSetting(java.lang
	 * .String, java.lang.Object[])
	 */
	@Override
	public String getSetting(String key, Object... args) {
		if (overridedSettings.containsKey(key)) {
			return overridedSettings.get(key);
		} else {
			return messageSource.getMessage(key, args, null);
		}
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @param overridedSettings
	 *            the overridedSettings to set
	 */
	public void setOverridedSettings(Map<String, String> overridedSettings) {
		this.overridedSettings = overridedSettings;
	}
}
