package com.hito.am.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	//@RequestMapping(path = "/index",method = RequestMethod.GET)
	@GetMapping({"/index", "/", "/home"})
	public String index(Model model) {
		model.addAttribute("title", " pagina demo");
		return "index";
	}
}
