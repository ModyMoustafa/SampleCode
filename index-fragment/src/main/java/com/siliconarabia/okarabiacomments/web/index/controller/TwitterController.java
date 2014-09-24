package com.siliconarabia.okarabiacomments.web.index.controller;

import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.siliconarabia.okarabiacomments.dto.CommentDTO;
import com.siliconarabia.okarabiacomments.dto.CommentHashDTO;
import com.siliconarabia.okarabiacomments.dto.SubCommentDTO;
import com.siliconarabia.okarabiacomments.dto.UserDTO;
import com.siliconarabia.okarabiacomments.utils.GeneralUtils;
import com.siliconarabia.okarabiacomments.web.util.CommentsConstants;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;
import com.siliconarabia.okarabiacomments.ws.user.api.UserWebService;

/**
 * <p>
 * Controller that handles all service calls that are twitter related. All
 * authorization calls are handled by the spring provided {@see
 * org.springframework.social.web.connect.ConnectController}.
 * </p>
 * 
 * @author Jettro Coenradie
 */
@Controller
public class TwitterController {
	private static final Logger log = LoggerFactory
			.getLogger(TwitterController.class);

	@Autowired
	private UserWebService userWebService;

	@Autowired
	private CommentWebService commentWebService;

	@Autowired
	private TwitterManager twitterManager;

	@RequestMapping(value = "/connect/twitter", method = RequestMethod.GET)
	public String requestConnectionToTwitter(WebRequest request, Model model,
			HttpSession session) {
		Token accessToken = (Token) session.getAttribute("twitterAccessToken");
		if (accessToken == null) {
			// get request token
			Token requestToken = twitterManager.getOAuthService()
					.getRequestToken();
			// store request token in session
			request.setAttribute("twitter_request_token", requestToken,
					WebRequest.SCOPE_SESSION);

			String twitterRedirectUrl = twitterManager.getAuthorizeUrl()
					+ "?oauth_token=" + requestToken.getToken();
			model.addAttribute("redirectUrl", twitterRedirectUrl);
			return "authenticationRedirectPage";
		} else {
			addTwitterComment(session, model);
			return "redirect:/index";
		}
	}

	/**
	 * Callback from twitter on success login
	 * 
	 * @param verifier
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/callback/twitter", method = RequestMethod.GET, params = "oauth_token")
	public String authorizeTwitterCallback(
			@RequestParam(value = "oauth_verifier", defaultValue = "verifier") String verifier,
			HttpSession session, Model model, WebRequest request) {
		String redirectUrl = (String) session
				.getAttribute(CommentsConstants.HOST_PAGE_REDIRECT_URL_SESSION_ATTRIBUTE);

		// get request token from session
		Token requestToken = (Token) request.getAttribute(
				"twitter_request_token", WebRequest.SCOPE_SESSION);

		// get access token
		Token accessToken = twitterManager.getOAuthService().getAccessToken(
				requestToken, new Verifier(verifier));
		session.setAttribute("twitterAccessToken", accessToken);

		addTwitterComment(session, model);

		return "redirect:" + redirectUrl;

	}

	public void addTwitterComment(HttpSession session, Model model) {

		String url = (String) session
				.getAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE);

		Token accessToken = (Token) session.getAttribute("twitterAccessToken");

		String commentBody = (String) session.getAttribute("commentBody");

		TwitterTemplate twitterTemplate = twitterManager
				.getTemplate(accessToken);

		String twitterName = twitterTemplate.getProfileId();
		UserDTO user = new UserDTO();
		user.setFullName(twitterName);
		log.debug(twitterName);
		user.setAvatar("http://api.twitter.com/1/users/profile_image/"
				+ twitterName);
		session.setAttribute("avatar_url", user.getAvatar());
		user.setProfileId(twitterName);
		user.setEmail("same@mail.com");
		user = userWebService.registerUser(user);
		session.setAttribute("currentUser", user);

		long commentId = (Long) session.getAttribute("commentId");
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