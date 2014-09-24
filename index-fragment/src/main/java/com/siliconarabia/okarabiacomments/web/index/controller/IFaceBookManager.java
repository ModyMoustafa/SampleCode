package com.siliconarabia.okarabiacomments.web.index.controller;

/**
 * 
 * @author Mohammed Salah
 * 
 */
public interface IFaceBookManager {

	public String retrieveAccessToken(String code);

	public String getFacebookAuthenticationUrl();
}
