package br.com.lenonsec.flashcursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReforcoController {
	
	@RequestMapping("/reforco")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaReforcos");
		mv.addObject("titulo", "Minha Lista de Reforços Dinâmica");
		return mv;
	}

}
