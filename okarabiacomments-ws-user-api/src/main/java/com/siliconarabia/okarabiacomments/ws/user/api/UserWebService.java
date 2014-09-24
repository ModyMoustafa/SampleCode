/***
 * 
 * 
 * @(#) UserWebService.java 5:50:14 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.siliconarabia.okarabiacomments.ws.user.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.pearlox.framework.ws.exception.ServiceException;
import com.siliconarabia.okarabiacomments.dto.UserDTO;

/**
 * 
 * @author dmitry & Michael Makram
 * 
 */
@WebService
@Path("/api/user")
public interface UserWebService {

	/**
	 * Registers a new user.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@WebMethod(operationName = "Register")
	@Path("/register")
	@WebResult(name = "result")
	public UserDTO registerUser(
			@WebParam(name = "user") @PathParam("user") UserDTO userDTO)
			throws ServiceException;

	/**
	 * Loads all users that are available in the system
	 * 
	 * @param userDTO
	 * @return list of user dto
	 */
	@WebMethod(operationName = "ListAll")
	@GET
	@Path("/list")
	@WebResult(name = "result")
	public List<UserDTO> listAllUsers() throws ServiceException;

	/**
	 * Find the user with the given profile id
	 * 
	 * @param profileId
	 * @return User
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "FindByProfileId")
	@GET
	@Path("/findByProfileId")
	@WebResult(name = "result")
	public UserDTO findUserByProfileId(
			@WebParam(name = "profileId") @PathParam("profileId") String profileId)
			throws ServiceException;

	/**
	 * Get Current user after login
	 * 
	 * @return Current User
	 */
	@WebMethod(operationName = "CurrentUserName")
	@GET
	@Path("/username")
	@WebResult(name = "result")
	public String getCurrentUserName();
}
