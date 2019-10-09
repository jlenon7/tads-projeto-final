package com.flashcursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfessorController {
	
	@RequestMapping("/professores")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaProfessores");
		mv.addObject("titulo", "Minha Lista de Professores Dinâmica");
		return mv;
	}

}
