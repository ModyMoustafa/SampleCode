/***
 * 
 * 
 * @(#) UserServiceImpl.java 5:53:16 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siliconarabia.okarabiacomments.dao.UserDao;
import com.siliconarabia.okarabiacomments.domain.User;
import com.siliconarabia.okarabiacomments.user.service.api.UserService;

/**
 * It is a default implementation of {@link UserService}. Feel free to modify
 * and delete it
 * 
 * @author dmitry
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.user.service.api.UserService#registerUser
	 * (com.pearlox .sample.domain.User)
	 */
	@Override
	public User registerUser(User user) {
		User temp = userDao.findUserByProfileId(user.getProfileId());
		if (temp != null) {
			return temp;
		} else {
			try {
				return userDao.saveOrUpdate(user);
			} catch (Exception e) {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.user.service.api.UserService#getAllUsers
	 * ()
	 */
	@Override
	public List<User> getAllUsers() {
		return userDao.findAllUsers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.user.service.api.UserService#
	 * findUserByProfileId(java.lang.String)
	 */
	@Override
	public User findUserByProfileId(String profileId) {
		return userDao.findUserByProfileId(profileId);
	}

}
