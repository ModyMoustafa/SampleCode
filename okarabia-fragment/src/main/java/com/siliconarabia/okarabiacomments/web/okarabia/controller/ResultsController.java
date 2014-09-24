package com.siliconarabia.okarabiacomments.web.okarabia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultsController {

	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String post(
			HttpServletRequest request,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "search_query", required = false) String keyword,
			Model model, HttpSession session) {

		if (language != null) {
			return "redirect:http://www.okarabia.com/results?search_query="
					+ keyword + "&place=Everywhere&cat=all&myHidden=&lang="
					+ language;
		} else {
			return "redirect:http://www.okarabia.com/results?search_query="
					+ keyword + "&place=Everywhere&cat=all&myHidden=";
		}

	}

}
