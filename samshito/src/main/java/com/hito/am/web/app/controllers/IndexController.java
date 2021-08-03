package com.hito.am.web.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
		

	// @RequestMapping(path = "/index",method = RequestMethod.GET)
	@GetMapping({ "/index", "/", "/home" })
	public String index(Model model) {
		logger.info("IndexController : indexindex");
		model.addAttribute("title", " pagina demo");
		return "index";
	}



}
