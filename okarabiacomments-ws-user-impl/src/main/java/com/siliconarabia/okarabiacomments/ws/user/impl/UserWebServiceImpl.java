/***
 * 
 * 
 * @(#) UserWebServiceImpl.java 6:06:01 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.ws.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pearlox.framework.converter.AnnotationConverter;
import com.pearlox.framework.converter.CollectionConverter;
import com.pearlox.framework.ws.exception.ServiceException;
import com.siliconarabia.okarabiacomments.domain.User;
import com.siliconarabia.okarabiacomments.dto.UserDTO;
import com.siliconarabia.okarabiacomments.user.service.api.UserService;
import com.siliconarabia.okarabiacomments.ws.user.api.UserWebService;
import com.siliconnile.security.client.SecuritySystemAuthToken;

/**
 * 
 * @author dmitry & Michael Makram
 * 
 */
public class UserWebServiceImpl implements UserWebService {
	@Autowired
	private AnnotationConverter annotationConverter;
	@Autowired
	private CollectionConverter collectionConverter;
	@Autowired
	private UserService userService;

	private static final Logger log = LoggerFactory
			.getLogger(UserWebServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.ws.user.api.UserWebService#registerUser
	 * (com.pearlox .sample.dto.UserDTO, java.lang.String)
	 */
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws ServiceException {
		try {
			User user = annotationConverter.getDomainObject(userDTO);
			user = userService.registerUser(user);
			userDTO = annotationConverter.getDTO(user);
			return userDTO;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.ws.user.api.UserWebService#listAllUsers
	 * (com.pearlox .sample.dto.UserDTO)
	 */
	@Override
	public List<UserDTO> listAllUsers() throws ServiceException {
		try {
			List<User> users = userService.getAllUsers();
			return collectionConverter.convertCollection(users);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.user.api.UserWebService#
	 * findUserByProfileId(java.lang.String)
	 */
	@Override
	public UserDTO findUserByProfileId(String profileId)
			throws ServiceException {
		try {

			User user = userService.findUserByProfileId(profileId);
			return annotationConverter.getDTO(user);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.siliconarabia.okarabiacomments.ws.user.api.UserWebService#getCurrentUserName()
	 */
	@Override
	public String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth instanceof SecuritySystemAuthToken) {
			com.siliconnile.security.dto.UserDTO user = (com.siliconnile.security.dto.UserDTO) auth.getPrincipal();
			return user.getFirstName() + " " + user.getLastName();
		}
		return null;
	}
}
