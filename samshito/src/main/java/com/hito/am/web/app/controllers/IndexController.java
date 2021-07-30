package com.hito.am.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hito.am.web.app.models.Usuario;

@Controller
public class IndexController {

	// @RequestMapping(path = "/index",method = RequestMethod.GET)
	@GetMapping({ "/index", "/", "/home" })
	public String index(Model model) {
		model.addAttribute("title", " pagina demo");
		return "index";
	}

	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario user = new Usuario();
		user.setNombre("Frank");
		user.setApellido("Trejo");
		// user.setEmail("frank_jr@live.com.mx");
		model.addAttribute("title", " Perfil de usuario");
		model.addAttribute("usuario", user);
		return "perfil";
	}

	@RequestMapping("/listar")
	public String listado(Model model) {
		
		model.addAttribute("usuarios", poblarUsuarios());
		return "listar";
	}

	public List<Usuario> poblarUsuarios() {
		List<Usuario> lsUsuarios = new ArrayList<Usuario>();
		for (int i = 0; i < 10; i++) {
			Usuario user = new Usuario();
			user.setNombre("Frank" + String.valueOf(i));
			user.setApellido("Apellido");
			user.setEmail("frank_jr@live.com.mx");
			lsUsuarios.add(user);

		}
		return lsUsuarios;
	}

}
