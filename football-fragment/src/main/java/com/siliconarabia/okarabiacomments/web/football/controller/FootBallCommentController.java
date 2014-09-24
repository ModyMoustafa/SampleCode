package com.siliconarabia.okarabiacomments.web.football.controller;

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

import com.siliconarabia.okarabiacomments.dto.CommentHashDTO;
import com.siliconarabia.okarabiacomments.dto.UserComment;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;

/**
 * 
 * @author Mody
 * 
 */
@Controller
public class FootBallCommentController {

	@Autowired
	private CommentWebService commentWebService;

	private static final Logger log = LoggerFactory
			.getLogger(FootBallCommentController.class);

	@RequestMapping(value = "footballComments", method = RequestMethod.GET)
	public String get(
			@RequestParam(value = "hashCode", required = false) String hashCode,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "leagueId", required = false) Long leagueId,
			@RequestParam(value = "teamAId", required = false) Long teamAId,
			@RequestParam(value = "teamBId", required = false) Long teamBId,
			Model model, HttpSession session) {

		// set commentHashDTO in session
		CommentHashDTO commentHashDTO = new CommentHashDTO();
		commentHashDTO.setCommentHashCode(hashCode);
		commentHashDTO.setCategory(category);
		commentHashDTO.setLeagueId(leagueId);
		commentHashDTO.setTeamAId(teamAId);
		commentHashDTO.setTeamBId(teamBId);
		session.setAttribute("commentHashDTO", commentHashDTO);

		// Filter Comments by key
		List<UserComment> commentList = null;
		Integer key = (Integer) session.getAttribute("key");

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

		if (key == 1) {
			commentList = commentWebService.getUserCommentByHashCode(hashCode,
					timeOffset);
		} else if (key == 2) {
			commentList = commentWebService.getUserCommentByCategory(category,
					timeOffset);
		} else if (key == 3) {
			commentList = commentWebService.getUserCommentByLeagueId(leagueId,
					timeOffset);
		} else if (key == 4) {
			commentList = commentWebService.getUserCommentByTeamId(teamAId,
					timeOffset);
		} else {
			commentList = null;
		}
		session.setAttribute("urlSession", hashCode);
		model.addAttribute("comments", commentList);
		return "/footballComments";
	}
}
