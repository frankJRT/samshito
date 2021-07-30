package com.hito.am.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComisionesController {

	@GetMapping({ "/comisiones" })
	public String index() {
		
		return "comisiones/index";
	}
	
	@PostMapping({ "/comisiones" })
	public String procesar(@RequestParam String cartera, @RequestParam String periodo,Model model) {
		System.out.println(cartera);
		System.out.println(periodo);
		return "comisiones/resultado";
	}	
}
