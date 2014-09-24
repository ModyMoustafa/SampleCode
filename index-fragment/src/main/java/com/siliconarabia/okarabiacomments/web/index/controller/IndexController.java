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
package com.siliconarabia.okarabiacomments.web.index.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siliconarabia.okarabiacomments.dto.UserComment;
import com.siliconarabia.okarabiacomments.web.util.CommentsConstants;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;

/**
 * Index Controller is responsible to show a main page of the application.
 * 
 * @author dmitry
 * 
 */
@Controller
public class IndexController {
	@Autowired
	private CommentWebService commentWebService;

	private static final Logger log = LoggerFactory
			.getLogger(IndexController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String initPage(
			Model model,
			HttpSession session,
			@RequestParam(value = "noc", required = false) Integer noc,
			// @RequestParam(value = "orgcom", required = true) boolean orgcom,

			@RequestParam(value = "w", required = false) Integer width,
			@RequestParam(value = "h", required = false) Integer height,
			@RequestParam(value = "containerid", required = false) String containerId,
			@RequestParam(value = "url", required = false) String commentURL) {
		log.info("CommentSite : : " + commentURL);

		// model.addAttribute("urlInput", url);
		String avatar = (String) session.getAttribute("avatar_url");
		if (avatar != null) {
			model.addAttribute("avatar", avatar);
		}
		// getting all comments
		String hashCode = (String) session
				.getAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE);
		// getting all comments
		// List<UserComment> commentList =
		// commentWebService.getUserCommentsByUrl(url);
		Integer timeOffset = null;
		if (session.getAttribute("timezoneUserOffset") != null) {
			log.info(">>>>>>>>>>>>>>>>timeOffset in Session != null <<<<<<<<<<<<<<<<<<<<");
			String timezoneOffset = (String) session
					.getAttribute("timezoneUserOffset");
			timeOffset = Integer.parseInt(timezoneOffset);
			log.info(">>>>>>>>>>>>>>>>timeOffset = <<<<<<<<<<<<<<<<<<<<"
					+ timeOffset);
		} else {
			log.info(">>>>>>>>>>>>>>>>timeOffset in Session == null <<<<<<<<<<<<<<<<<<<<");
			timeOffset = 0;
			log.info(">>>>>>>>>>>>>>>>timeOffset = <<<<<<<<<<<<<<<<<<<<"
					+ timeOffset);
		}

		List<UserComment> commentList = commentWebService
				.getUserCommentByHashCode(hashCode, timeOffset);
		// log.info("List Size : : "+commentList.size());
		// if (orgcom == true){
		if (commentList != null && !commentList.isEmpty()
				&& commentList.size() > noc) {
			model.addAttribute("originalListSize", commentList.size());
			commentList = commentList.subList(commentList.size() - noc,
					commentList.size());
		}
		model.addAttribute("w", width);
		model.addAttribute("h", height);
		model.addAttribute("containerid", containerId);
		model.addAttribute("commentURL", commentURL);
		// }
		if (commentList != null && commentList.isEmpty()) {
			commentList = null;
		}
		model.addAttribute("comments", commentList);
		session.setAttribute("commentHashDTO", null);
		// model.addAttribute("orgcom", orgcom);
		return "/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String post(
			HttpSession session,
			Model model,
			@RequestParam(value = "commentBody", required = false) String commentBody,
			@RequestParam(value = "commentId", required = false, defaultValue = "-1") long commentId,
			@RequestParam(value = "redirect", required = false) String redirect,
			@RequestParam(value = "pageRedirectUrl") String pageRedirectUrl) {
		session.setAttribute("commentBody", commentBody);
		session.setAttribute("commentId", commentId);
		session.setAttribute(
				CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE,
				pageRedirectUrl.replace("#", ""));

		return redirect;
	}

}
