package com.siliconarabia.okarabiacomments.web.index.controller;

import org.mortbay.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrganizeCommentsCont {
	@RequestMapping(method = RequestMethod.GET, value = "/organizeComments")
	public void get(Model model, 
					@RequestParam(value = "noc", required = true) String noc,
					@RequestParam(value = "w", required = true) String w,
					@RequestParam(value = "h", required = true) String h,
					@RequestParam(value = "containerid", required = true) String containerid,
					@RequestParam(value = "url", required = true) String url) {
		Log.info("$$$$$-----noc-----$$$$$"+noc);
		Log.info("$$$$$-----w-----$$$$$"+w);
		Log.info("$$$$$-----h-----$$$$$"+h);
		Log.info("$$$$$-----containerid-----$$$$$"+containerid);
		Log.info("$$$$$-----url-----$$$$$"+url);
		
		model.addAttribute("noc", noc);
		model.addAttribute("w", w);
		model.addAttribute("h", h);
		model.addAttribute("containerid", containerid);
		model.addAttribute("url", url);
		
	}
}
