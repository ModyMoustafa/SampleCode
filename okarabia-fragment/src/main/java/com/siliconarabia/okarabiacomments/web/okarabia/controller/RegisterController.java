package com.siliconarabia.okarabiacomments.web.okarabia.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siliconnile.security.dto.NewUserRequest;
import com.siliconnile.security.dto.UserDTO;
import com.siliconnile.security.ws.user.api.UserWebService;

@Controller
public class RegisterController {

	@Autowired
	UserWebService userWebService;

	private static final Logger log = LoggerFactory
			.getLogger(RegisterController.class);

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String get(HttpSession session,
			@RequestParam(value = "language", required = false) String language) {
		log.info("Into Get Method");
		if (language != null) {
			session.setAttribute("lang", language);
		} else {
			session.setAttribute("lang", "en");
		}
		return "/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String post(
			HttpSession session,
			Model model,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "language", required = false) String language) {
		log.info("Into Post Method");
		log.info("email" + email);
		log.info("password" + password);
		log.info("language" + language);

		if (email != null && password != null) {
			NewUserRequest userRequest = new NewUserRequest();
			userRequest.setPassword(password);
			userRequest.setEmail(email);
			if (!userWebService.checkUserExists(email)) {
				UserDTO userDto = userWebService.registerUser(userRequest);
				log.info("---------------------------------" + userDto.getId());
			} else {
				return "register";
			}
		}
		return "redirect:/";

	}
}
