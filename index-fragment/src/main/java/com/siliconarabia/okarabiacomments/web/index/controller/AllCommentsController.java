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
 * 
 * @author Mody
 * 
 */
@Controller
public class AllCommentsController {

	@Autowired
	CommentWebService commentWebService;

	private static final Logger log = LoggerFactory
			.getLogger(AllCommentsController.class);

	/**
	 * 
	 * @param commentUrl
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "allcomments", method = RequestMethod.GET)
	public String getAllComments(
			@RequestParam(value = "commetnUrl", required = true) String commentUrl,
			Model model, HttpSession session) {

		// Set default avatar
		String avatar = (String) session.getAttribute("avatar_url");
		if (avatar != null) {
			model.addAttribute("avatar", avatar);
		}

		log.info("CommentSite : : " + commentUrl);
		String hashCode = (String) session
				.getAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE);

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

		// get all comments and subcomments
		List<UserComment> commentList = commentWebService
				.getUserCommentByHashCode(hashCode, timeOffset);

		model.addAttribute("comments", commentList);
		return "allcomments";

	}
}
