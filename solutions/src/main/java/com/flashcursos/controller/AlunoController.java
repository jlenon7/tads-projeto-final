package com.flashcursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlunoController {
	
	@RequestMapping("/alunos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaAlunos");
		mv.addObject("titulo", "Minha Lista de Alunos Dinâmica");
		return mv;
	}

}
