package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcursos.model.entity.Curso;
import com.flashcursos.model.repository.CursoRepository;

@Service
@Transactional
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	/**
	 * Serviço para listar os cursos cadastrados
	 * @return
	 */
	public List<Curso> listarCursos(){
		return this.cursoRepository.findAll();
	}
	
	/**
	 * Serviço para inserir um novo curso
	 * @return
	 */
	public Curso cadastrarCurso(Curso curso) {
		return this.cursoRepository.save(curso);
	}


}
