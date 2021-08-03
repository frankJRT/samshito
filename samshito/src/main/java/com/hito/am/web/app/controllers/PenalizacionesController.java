package com.hito.am.web.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hito.am.web.app.models.model.ModelPenalizacionesProcess;
import com.hito.am.web.app.services.CarteraService;
import com.hito.am.web.app.services.PenalizacionesService;

@Controller
@RequestMapping("/penalizaciones")
public class PenalizacionesController {

	private static final Logger logger = LoggerFactory.getLogger(CarteraService.class);
	
	@Value("${catalogoComportamiento.I_administrador}")
	private String iAdministrador;

	@Value("${catalogoComportamiento.I_IntermediarioFinanciero}")
	private String iIntermediarioFinanciero;

	@Autowired
	private CarteraService carteraService;

	@Autowired
	private PenalizacionesService penalizacionesService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("PenalizacionesController : index ");
		model.addAttribute("carteras", carteraService.getCarteras());
		model.addAttribute("periodos", penalizacionesService.getPeriodos());
		model.addAttribute("modelPenalizacionesProcess", new ModelPenalizacionesProcess());
		return "penalizaciones/index";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView execPythonProcess(ModelPenalizacionesProcess modelPenalizacionesProcess, BindingResult result) {
		ModelAndView model = new ModelAndView();
		System.out.println(modelPenalizacionesProcess.getCartera());
		System.out.println(modelPenalizacionesProcess.getPeriodo());
		System.out.println(modelPenalizacionesProcess.getProceso());
		penalizacionesService.callPythonProcess();
		model.addObject("modelPenalizacionesProcess", modelPenalizacionesProcess);
		model.setViewName("/penalizaciones/result");
		
		
		return model;
	}

}
