/***
 * 
 * 
 * @(#) SampleDaoImpl.java 5:32:58 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pearlox.framework.dao.impl.BasicJpaDao;
import com.siliconarabia.okarabiacomments.dao.UserDao;
import com.siliconarabia.okarabiacomments.domain.User;

/**
 * 
 * @author dmitry & Michael Makram
 * 
 */
@Repository
public class UserDaoImpl extends BasicJpaDao<User> implements UserDao {

	/**
	 * Default constructor
	 */
	public UserDaoImpl() {
		super(User.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.SampleDao#findAllUsers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		return getEntityManager().createQuery("FROM User").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.UserDao#findUserByProfileId(java
	 * .lang.String)
	 */
	@Override
	public User findUserByProfileId(String profileId) {
		String qry = "from User where profile_id =:profileId";
		Query query = getEntityManager().createQuery(qry);
		query.setParameter("profileId", profileId);
		User user;
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return user;
	}
}
