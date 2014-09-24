package com.siliconarabia.okarabiacomments.web.football.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Mody
 * 
 */
@Controller
public class FootBallCommentLoader {
	private static Integer DEFAULT_IFRAME_WIDTH = 400;
	private static Integer DEFAULT_IFRAME_HEIGHT = 500;

	@RequestMapping(value = "/football-comment-loader.js", method = RequestMethod.GET)
	public void initPage(
			@RequestParam(value = "w", required = false) Integer width,
			@RequestParam(value = "h", required = false) Integer height,
			@RequestParam(value = "containerid") String containerId,
			@RequestParam(value = "hashCode", required = false) String hashCode,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "leagueId", required = false) Long leagueId,
			@RequestParam(value = "teamAId", required = false) Long teamAId,
			@RequestParam(value = "teamBId", required = false) Long teamBId,
			@RequestParam(value = "key", required = false) int key,
			@RequestParam(value = "timezoneOffset", required = false) String timezoneOffset,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		
		// Check the timezone if Exist
		if (timezoneOffset != null && !timezoneOffset.isEmpty()) {
			request.getSession().setAttribute("timezoneUserOffset", timezoneOffset);
		}
		
		
		StringBuffer result = new StringBuffer();
		result.append("IFRAME_CONTAINER_ID = \"").append(containerId)
				.append("\";\n");
		result.append("HashCode = \"").append(hashCode).append("\";\n");
		result.append("Category = \"").append(category).append("\";\n");
		result.append("LeagueId = \"").append(leagueId).append("\";\n");
		result.append("TeamAId = \"").append(teamAId).append("\";\n");
		result.append("TeamBId = \"").append(teamBId).append("\";\n");
		result.append("Key = \"").append(key).append("\";\n");
		result.append("IFRAME_WIDTH = ")
				.append(width != null ? width : DEFAULT_IFRAME_WIDTH)
				.append(";\n");
		result.append("IFRAME_HEIGHT = ")
				.append(height != null ? height : DEFAULT_IFRAME_HEIGHT)
				.append(";\n");

		StringBuffer requestUrl = request.getRequestURL();
		String domainPart = requestUrl.substring(0,
				requestUrl.indexOf(request.getRequestURI()));
		request.getSession().setAttribute("urlSession", hashCode);
		request.getSession().setAttribute("key", key);
		result.append("IFRAME_SRC = \"")
				.append(domainPart)
				.append("/footballComments?leagueId=" + leagueId + "&teamAId="
						+ teamAId + "&teamBId=" + teamBId + "&category="
						+ category + "&hashCode=" + hashCode + "\";\n");
		result.append("\n");

		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				"WEB-INF/js/footballCommentLoader.js");
		byte[] buff = new byte[1024];
		int read = 0;

		while ((read = stream.read(buff, 0, 1024)) >= 0) {
			String s = new String(buff, 0, read);
			result.append(s);
		}

		stream.close();

		response.setContentType("text/javascript");
		response.getOutputStream().write(result.toString().getBytes());
		response.flushBuffer();
	}

	@RequestMapping(value = "/testFootballFrame", method = RequestMethod.GET)
	public String get(Model model, HttpSession session) {
		return "/testFootballFrame";
	}
}
