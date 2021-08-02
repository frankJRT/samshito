package com.hito.am.web.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hito.am.web.app.models.entity.Cartera;
import com.hito.am.web.app.models.model.ModelCartera;
import com.hito.am.web.app.services.CarteraService;

@Controller
@RequestMapping("/cartera")
public class CarteraController {

	private static final Logger logger = LoggerFactory.getLogger(CarteraService.class);

	@Autowired
	private CarteraService carteraService;

	@RequestMapping("/")
	// @ResponseBody
	public String index(Model model) {
		model.addAttribute("carteras", carteraService.getCarteras());

		return "carteras/index";
	}

	/*
	 * @RequestMapping("/getCarteras")
	 * 
	 * @ResponseBody public List<ModelCartera> getCarteras(Model model) { return
	 * carteraService.getCarteras();
	 * 
	 * }
	 */

	@RequestMapping("/getCatalogos")
	public String getCatalogos(Model model) {
		model.addAttribute("catalogos", carteraService.getCatalogos());
		return "carteras/catalogos";

	}

	@RequestMapping("/elementoscatalogo")
	public String getElementosCatalogo(@RequestParam String id, @RequestParam String nombre, Model model) {
		model.addAttribute("title", nombre);
		int cat = 0;
		try {
			cat = Integer.parseInt(id);

		} catch (NumberFormatException e) {
			return "error/401";
		}

		model.addAttribute("elementos", carteraService.getElementosCatalogo(cat));
		return "carteras/elementoscatalogo";

	}
}
