package com.siliconarabia.okarabiacomments.web.index.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siliconarabia.okarabiacomments.web.util.CommentsConstants;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;

@Controller
public class RemoveCommentController {

	@Autowired
	private CommentWebService commentWebService;

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String deleteComment(
			HttpSession session,
			@RequestParam(value = "commentId", required = false) Long commentId,
			@RequestParam(value = "url", required = false) String url) {
		session.setAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE, url);
		commentWebService.deleteComment(commentId);
		return "redirect:/index?noc=3";

	}

	@RequestMapping(value = "/removesub", method = RequestMethod.GET)
	public String deleteSubComment(
			HttpSession session,
			@RequestParam(value = "commentId", required = false) Long commentId,
			@RequestParam(value = "url", required = false) String url) {

		commentWebService.deleteSubComment(commentId);
		return "redirect:/index?noc=3";

	}
}
