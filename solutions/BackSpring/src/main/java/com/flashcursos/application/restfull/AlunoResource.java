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

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.service.AlunoService;


@Component
@RestController
@RequestMapping( "/api/admin/aluno" )
public class AlunoResource {
	
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping("/list")
	public List<Aluno> listar() {
		return this.alunoService.listarAlunos();
	}
	
	@GetMapping("/find")
	public Aluno detalhar(@RequestParam("id") Long id) {
		return this.alunoService.detalharAluno(id);
	}
	
	
	@GetMapping("/remove")
	public void remover(@RequestParam("id") Long id) {
		this.alunoService.removerAluno(id);
	}
	
	@PostMapping( "/insert" )
	public Aluno cadastrar( @RequestBody Aluno aluno )
	{
		return this.alunoService.cadastrarAluno(aluno);
	}
	

	@PostMapping( "/update" )
	public Aluno atualizar( @RequestBody Aluno aluno )
	{
		return this.alunoService.atualizarAluno(aluno);
	}
}



