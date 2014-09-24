/***
 * 
 * 
 * @(#) SampleDao.java 5:27:39 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.dao;

import java.util.List;

import com.pearlox.framework.dao.BasicDao;
import com.siliconarabia.okarabiacomments.domain.User;

/**
 * 
 * @author dmitry & Michael
 * 
 */
public interface UserDao extends BasicDao<User> {

	/**
	 * Returns all users available
	 * 
	 * @return
	 */
	public List<User> findAllUsers();

	/**
	 * Returns all user by profile id
	 * 
	 * @author michael
	 * @return User
	 */
	public User findUserByProfileId(String profileId);

}
