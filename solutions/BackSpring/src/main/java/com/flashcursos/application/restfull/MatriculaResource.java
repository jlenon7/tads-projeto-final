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

import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.service.MatriculaService;


@Component
@RestController
@RequestMapping( "/api/admin/matricula" )
public class MatriculaResource {
	
	
	@Autowired
	private MatriculaService matriculaService;
	
	@GetMapping("/list")
	public List<MatriculaCurso> listar() {
		return this.matriculaService.listarMatricula();
	}
	
	@GetMapping("/find")
	public MatriculaCurso detalhar(@RequestParam("id") Long id) {
		return this.matriculaService.detalharMatricula(id);
	}
	
	
	@GetMapping("/remove")
	public void remover(@RequestParam("id") Long id) {
		this.matriculaService.removerMatricula(id);
	}
	
	@PostMapping( "/insert" )
	public MatriculaCurso cadastrar( @RequestBody MatriculaCurso matricula ) {
		return this.matriculaService.cadastrarMatricula(matricula);
	}
	

	@PostMapping( "/update" )
	public MatriculaCurso atualizar( @RequestBody MatriculaCurso matricula ) {
		return this.matriculaService.atualizarMatricula(matricula);
	}
}



