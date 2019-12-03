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

import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.service.ProfessorService;


@Component
@RestController
@RequestMapping( "/api/admin/professor" )
public class ProfessorResource {
	
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/list")
	public List<Professor> listar() {
		return this.professorService.listarProfessores();
	}
	
	@GetMapping("/find")
	public Professor detalhar(@RequestParam("id") Long id) {
		return this.professorService.detalharProfessor(id);
	}
	
	
	@GetMapping("/remove")
	public void remover(@RequestParam("id") Long id) {
		this.professorService.removerProfessor(id);
	}
	
	@PostMapping( "/insert" )
	public Professor cadastrar( @RequestBody Professor professor )
	{
		return this.professorService.cadastrarProfessor(professor);
	}
	

	@PostMapping( "/update" )
	public Professor atualizar( @RequestBody Professor professor )
	{
		return this.professorService.atualizarProfessor(professor);
	}
}



