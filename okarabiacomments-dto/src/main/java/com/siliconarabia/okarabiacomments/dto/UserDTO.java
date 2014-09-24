/***
 * 
 * 
 * @(#) UserDTO.java 5:18:09 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.pearlox.framework.converter.DomainClassRef;
import com.pearlox.framework.dto.BasicDTO;

/**
 * 
 * @author ALi M
 * 
 */
@SuppressWarnings("serial")
@XmlRootElement
@DomainClassRef(name = "User")
public class UserDTO extends BasicDTO {

	private Long id;
	private String email;
	private String avatar;
	private String fullName;
	private String profileId;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + "]";
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * @param profileId
	 *            the profile id to set
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	/**
	 * 
	 * @return the profileId
	 */
	public String getProfileId() {
		return profileId;
	}

}
