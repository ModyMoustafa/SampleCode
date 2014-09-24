/***
 * 
 * 
 * @(#) UserService.java 5:50:14 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.user.service.api;

import java.util.List;

import com.siliconarabia.okarabiacomments.domain.User;

/**
 * 
 * @author dmitry & Michael Makram
 * 
 */
public interface UserService {

	/**
	 * Registers new user
	 * 
	 * @param user
	 */
	public User registerUser(User user);

	/**
	 * Loads all users that are available
	 * 
	 * @return list of all users available
	 */
	public List<User> getAllUsers();

	/**
	 * Find the user with the given profile id
	 * 
	 * @param profileId
	 * @return User
	 */
	public User findUserByProfileId(String profileId);

}
