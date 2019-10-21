package br.com.lenonsec.flashcursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CursoController {
	
	@RequestMapping("/cursos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaCursos");
		mv.addObject("titulo", "Minha Lista de Cursos Dinâmica");
		return mv;
	}

}
