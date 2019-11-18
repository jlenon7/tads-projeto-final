package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.Curso;
import com.flashcursos.model.repository.CursoRepository;

@Service
@Transactional
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	/**
	 * Serviço para inserir um novo curso
	 * 
	 * @param curso
	 * @return
	 */
	public Curso cadastrarCurso(Curso curso) {
		return this.cursoRepository.save(curso);
	}
	
	/**
	 * Serviço para atualizar o cadastro de um curso
	 * @param curso
	 * @return
	 */
	public Curso atualizarCurso(Curso curso) {
		return this.cursoRepository.save(curso);
	}
	
	/**
	 * Serviço para listar os cursos cadastrados
	 * @return
	 */
	public List<Curso> listarCursos(){
		return this.cursoRepository.findAll();
	}
	

	/**
	 * Serviço para detalhar o cadastro de um curso
	 * @param id
	 * @return
	 */
	public Curso detalharCurso(long id) {	
		Curso  curso = this.cursoRepository.findById(id).orElse(null);	
		Assert.notNull(curso, "O ID "+ id +" não foi encontrado.");
		return curso;
	}
	
	/**
	 * Serviço que remove um curso cadastrado
	 * @param id
	 */
	public void removerCurso(long id) {
		this.cursoRepository.deleteById(id);
	}
	
}
