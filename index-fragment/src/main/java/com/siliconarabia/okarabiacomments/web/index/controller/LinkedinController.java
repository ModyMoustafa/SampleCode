package com.siliconarabia.okarabiacomments.web.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.LinkedInProfile;
import org.springframework.social.linkedin.LinkedInTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siliconarabia.okarabiacomments.dto.CommentDTO;
import com.siliconarabia.okarabiacomments.dto.CommentHashDTO;
import com.siliconarabia.okarabiacomments.dto.SubCommentDTO;
import com.siliconarabia.okarabiacomments.dto.UserDTO;
import com.siliconarabia.okarabiacomments.utils.GeneralUtils;
import com.siliconarabia.okarabiacomments.web.util.CommentsConstants;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;
import com.siliconarabia.okarabiacomments.ws.user.api.UserWebService;

/**
 * Linkedin Controller. Handles requests for the linkedin login and user data.
 * 
 * @author Michael
 */
@Controller
@SessionAttributes("linkedinProfile")
public class LinkedinController {
	private static final Logger log = LoggerFactory
			.getLogger(LinkedinController.class);
	@Autowired
	private ILinkedinManager linkedinManager;
	@Autowired
	private UserWebService userWebService;
	@Autowired
	private CommentWebService commentWebService;

	@RequestMapping(value = "/linkedinHandler/login", method = RequestMethod.GET)
	public String autoLoginToLinkedin(Model model,
			@RequestParam("token") String token) {
		log.debug("linkedin login page is opened");
		String linkedintAuthenticationUrl = linkedinManager
				.getLinkedintAuthenticationUrl(token);
		model.addAttribute("redirectUrl", linkedintAuthenticationUrl);
		return "authenticationRedirectPage";
	}

	@RequestMapping(value = "/linkedin", method = RequestMethod.GET)
	public String attack(
			@RequestParam(value = "oauth_verifier", defaultValue = "verifier", required = false) String verifier,
			@RequestParam(value = "error_reason", required = false) String errorReason,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "error_description", required = false) String errorDescription,
			Model model, HttpServletRequest request, HttpSession session) {
		String url = (String) session
				.getAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE);
		log.debug("url in linkedin controller >> " + url);
		String redirectUrl = (String) session
				.getAttribute(CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE);
		log.debug("redirectUrl in linkedin controller >> " + redirectUrl);

		String commentBody = (String) session.getAttribute("commentBody");
		long commentId = (Long) session.getAttribute("commentId");
		if (errorReason != null) {
			log.info("user refused to allow the application access his account");
			return "redirect:" + redirectUrl;
		}

		Token accessToken = (Token) session.getAttribute("linkedinAccessToken");
		if (accessToken == null) {
			Token requestToken = (Token) session
					.getAttribute("linkedinRequestToken");
			accessToken = linkedinManager.retrieveAccessToken(requestToken,
					verifier);
			if (accessToken == null) {
				requestToken = linkedinManager.retrieveRequestToken();
				session.setAttribute("linkedinRequestToken", requestToken);
				return "redirect:/linkedinHandler/login?token="
						+ requestToken.getToken();
			}
		} else {
			// access token already received for the given user
			UserDTO user = prepareLinkedinUser(accessToken);
			postComment(url, commentBody, user, session, commentId);
			session.setAttribute("linkedinAccessToken", accessToken);
			return "redirect:/index";
		}
		session.setAttribute("linkedinAccessToken", accessToken);
		UserDTO user = prepareLinkedinUser(accessToken);
		postComment(url, commentBody, user, session, commentId);
		return "redirect:" + redirectUrl;
	}

	private UserDTO prepareLinkedinUser(Token accessToken) {
		LinkedInTemplate linkedin = new LinkedInTemplate(
				linkedinManager.getApiKey(), linkedinManager.getApiSecret(),
				accessToken.getToken(), accessToken.getSecret());
		LinkedInProfile profile = linkedin.getUserProfile();
		UserDTO user = new UserDTO();
		user.setFullName(profile.getFirstName() + " " + profile.getLastName());
		user.setProfileId(profile.getId());
		user.setAvatar("/images/linkedIn.png");
		return user;
	}

	private void postComment(String url, String commentBody, UserDTO user,
			HttpSession session, long commentId) {
		user = userWebService.registerUser(user);
		session.setAttribute("currentUser", user);
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

	@RequestMapping(value = "/linkedin", method = RequestMethod.POST)
	public String attack(@RequestParam("error_reason") String errorReason,
			@RequestParam("error") String error,
			@RequestParam("error_description") String errorDescription,
			Model model, HttpServletRequest request, HttpSession session) {
		String redirectUrl = (String) session
				.getAttribute(CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE);
		log.debug("errorReason: " + errorReason);
		log.debug("error: " + error);
		log.debug("errorDescription: " + errorDescription);
		log.debug("in error");
		return "redirect:" + redirectUrl;
	}
}
