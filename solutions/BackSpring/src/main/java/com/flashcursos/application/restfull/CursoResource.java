package com.flashcursos.application.restfull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashcursos.model.entity.Curso;
import com.flashcursos.model.service.CursoService;


@Component
@RestController
@RequestMapping( "/api/admin/curso" )
public class CursoResource {
	
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/list")
	public List<Curso> listar() {
		return this.cursoService.listarCursos();
	}
	
	@GetMapping("/find")
	public Curso detalhar(@RequestParam("id") Long id) {
		return this.cursoService.detalharCurso(id);
	}
	
	
	@GetMapping("/remove")
	public void remover(@RequestParam("id") Long id) {
		this.cursoService.removerCurso(id);
	}
	
	@PostMapping( "/insert" )
	public Curso cadastrar( @RequestBody Curso curso )
	{
		return this.cursoService.cadastrarCurso(curso);
	}
	

	@PostMapping( "/update" )
	public Curso atualizar( @RequestBody Curso curso )
	{
		return this.cursoService.atualizarCurso(curso);
	}
}



