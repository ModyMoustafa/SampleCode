/***
 * 
 * 
 * @(#) IndexController.java 6:50:26 PM Sep 24, 2010
 * 
 * Copyright (c) 2010 Pearlox Corp. All rights reserved.
 * 
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.siliconarabia.okarabiacomments.web.okarabia.controller;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siliconarabia.okarabiacomments.dto.CommentDTO;
import com.siliconarabia.okarabiacomments.dto.CommentHashDTO;
import com.siliconarabia.okarabiacomments.dto.SubCommentDTO;
import com.siliconarabia.okarabiacomments.dto.UserDTO;
import com.siliconarabia.okarabiacomments.utils.GeneralUtils;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;
import com.siliconarabia.okarabiacomments.ws.user.api.UserWebService;

/**
 * 
 * @author michael
 * 
 */
@Controller
public class LoginController {

	@Autowired
	private UserWebService userWebService;

	@Autowired
	private com.siliconnile.security.ws.user.api.UserWebService userWebService2;
	@Autowired
	private CommentWebService commentWebService;

	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);

	@PostConstruct
	public void init() {

	}

	/**
	 * 
	 * @param model
	 * @param principal
	 * @param login_fail
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String initPage(
			Model model,
			Principal principal,
			HttpSession session,
			@RequestParam(value = "login_fail", required = false) String login_fail,
			@RequestParam(value = "language", required = false) String language) {

		if (userWebService2.findCurrentUser() == null) {
			log.info("Inside the Get Of login");

			if (language != null) {
				session.setAttribute("lang", language);
			} else {
				session.setAttribute("lang", "en");
			}
			return "login";
		} else {
			return "/redirect";
		}

	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String post(
			Model model,
			Principal principal,
			HttpSession session,
			@RequestParam(value = "login_fail", required = false) String login_fail,
			@RequestParam(value = "language", required = false) String language) {
		log.info("Inside the Post Of login");
		String commentBody = (String) session.getAttribute("commentBody");
		log.info("commentBody: " + commentBody);
		String url = (String) session.getAttribute("urlSession");
		log.info("redirectUrlSession: " + url);
		if (principal != null) {
			String userName = userWebService.getCurrentUserName();
			model.addAttribute("username", userName);
			log.info("USERNAME: " + userName);
			postcomment(session, url, commentBody);
		}
		if (login_fail != null) {
			model.addAttribute("login_fail", login_fail);
			return "login";
		}

		String pageRedirectUrl = (String) session
				.getAttribute("redirectUrlSession");
		log.info("pageRedirectUrl:" + pageRedirectUrl);

		return "redirect:" + pageRedirectUrl;

	}

	private void postcomment(HttpSession session, String url, String commentBody) {
		UserDTO user = new UserDTO();
		com.siliconnile.security.dto.UserDTO userSSO = userWebService2
				.findCurrentUser();
		user.setEmail(userSSO.getEmail());
		user.setProfileId("" + userSSO.getId());
		user.setFullName(userSSO.getEmail());
		user.setAvatar("/images/okarabia.png");
		session.setAttribute("avatar_url", user.getAvatar());
		user = userWebService.registerUser(user);
		session.setAttribute("currentUser", user);
		long commentId = (Long) session.getAttribute("commentId");
		log.info("commentId " + commentId);
		if (commentId < 0) {
			CommentHashDTO commentHashDTO = new CommentHashDTO();
			if (session.getAttribute("commentHashDTO") == null) {
				commentHashDTO.setCommentHashCode(url);
				commentHashDTO.setLeagueId(0l);
				commentHashDTO.setTeamAId(0l);
				commentHashDTO.setTeamBId(0l);
				commentHashDTO = commentWebService
						.checkHashCode(commentHashDTO);
			} else {
				commentHashDTO = (CommentHashDTO) session
						.getAttribute("commentHashDTO");
				commentHashDTO = commentWebService
						.checkHashCode(commentHashDTO);
			}
			CommentDTO comment = new CommentDTO();
			comment.setUserId(user.getId());
			comment.setCommentHashId(commentHashDTO.getId());
			comment.setCommentBody(commentBody);
			comment.setDateSent(GeneralUtils.getCurrentTimeGMT());
			commentWebService.postComment(comment);
		} else {
			SubCommentDTO comment = new SubCommentDTO();
			comment.setUserId(user.getId());
			comment.setCommentId(commentId);
			comment.setCommentBody(commentBody);
			comment.setDateSent(GeneralUtils.getCurrentTimeGMT());
			commentWebService.postSubComment(comment);
		}
	}
}
