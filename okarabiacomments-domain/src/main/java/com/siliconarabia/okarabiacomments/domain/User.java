/***
 * 
 * 
 * @(#) User.java 5:16:55 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pearlox.framework.converter.DTOClassRef;
import com.pearlox.framework.converter.DTOFieldRef;
import com.pearlox.framework.domain.BasicObject;

/**
 * 
 * @author dmitry & Michael Makram
 * 
 */
@SuppressWarnings("serial")
@Entity
@DTOClassRef(name = "UserDTO")
@Table(name = "user", schema = "public")
public class User extends BasicObject {

	@Column(name = "email")
	private String email;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "profile_id")
	private String profileId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<Comment> comments = new HashSet<Comment>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<SubComment> subComments = new HashSet<SubComment>(0);

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	@DTOFieldRef(fieldName = "email")
	public String getEmail() {
		return email;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	/**
	 * @return the subComments
	 */
	public Set<SubComment> getSubComments() {
		return subComments;
	}

	/**
	 * @param subComments
	 *            the subComments to set
	 */
	public void setSubComments(Set<SubComment> subComments) {
		this.subComments = subComments;
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
	@DTOFieldRef(fieldName = "fullName")
	public String getFullName() {
		return fullName;
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
	@DTOFieldRef(fieldName = "avatar")
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 
	 * @param profileId
	 *            the profile id to set
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@DTOFieldRef(fieldName = "profileId")
	public String getProfileId() {
		return profileId;
	}
}
