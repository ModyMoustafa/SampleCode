package com.siliconarabia.okarabiacomments.web.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.FacebookProfile;
import org.springframework.social.facebook.FacebookTemplate;
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
 * 
 * @author hossam
 * 
 */
@Controller
@SessionAttributes("facebookProfile")
public class FacebookController {
	private static final Logger log = LoggerFactory
			.getLogger(FacebookController.class);
	@Autowired
	private IFaceBookManager fBStuffKeeper;
	@Autowired
	private UserWebService userWebService;
	@Autowired
	private CommentWebService commentWebService;

	@RequestMapping(value = "/fbHandler/login", method = RequestMethod.GET)
	public String autoLoginToFB(Model model) {
		log.info("facebook login page is opened");
		String fbAuthUrl = fBStuffKeeper.getFacebookAuthenticationUrl();
		model.addAttribute("redirectUrl", fbAuthUrl);
		return "authenticationRedirectPage";
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String attack(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "error_reason", required = false) String errorReason,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "error_description", required = false) String errorDescription,
			Model model, HttpServletRequest request, HttpSession session) {

		String commentBody = (String) session.getAttribute("commentBody");
		String url = (String) session
				.getAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE);
		String redirectUrl = (String) session
				.getAttribute(CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE);
		log.info("URL in session >>>>> " + url);
		log.info("redirectUrl in session >>>>> " + redirectUrl);

		if (errorReason != null) {
			log.info("user refused to allow the application access his account");
			return "redirect:/index?url=" + url + "&redirectUrl=" + redirectUrl;
		}
		String accessToken = fBStuffKeeper.retrieveAccessToken(code);
		if (accessToken == null) {
			return "redirect:/fbHandler/login";
		} else {
			FacebookTemplate facebook = new FacebookTemplate(accessToken);
			FacebookProfile profile = facebook.getUserProfile();
			String email = profile.getEmail();
			UserDTO user = new UserDTO();
			user.setEmail(email);
			user.setProfileId("" + profile.getId());
			user.setFullName(profile.getName());
			user.setAvatar("https://graph.facebook.com/" + profile.getId()
					+ "/picture?type=small");
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
			String pageRedirectUrl = (String) session
					.getAttribute(CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE);
			return "redirect:" + pageRedirectUrl;
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateStatus(@RequestParam("status") String fbStatus,
			@RequestParam("accesstoken") String accessToken) {
		FacebookTemplate facebook = new FacebookTemplate(accessToken);
		facebook.updateStatus(fbStatus);
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.POST)
	public String attack(@RequestParam("error_reason") String errorReason,
			@RequestParam("error") String error,
			@RequestParam("error_description") String errorDescription,
			Model model, HttpServletRequest request, HttpSession session) {
		String redirectUrl = (String) session
				.getAttribute(CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE);
		log.debug("errorReason:" + errorReason);
		log.debug("error: " + error);
		log.debug("errorDescription: " + errorDescription);
		log.debug("in error");
		return "redirect:" + redirectUrl;
	}
}
