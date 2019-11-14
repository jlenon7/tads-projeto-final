package com.flashcursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	
	@RequestMapping("/usuarios")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaUsuarios");
		mv.addObject("titulo", "Minha Lista de Usuarios Dinâmica");
		return mv;
	}

}



