package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.repository.MatriculaRepository;

@Service
@Transactional
public class MatriculaService {
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	/**
	 * Serviço para listar as matriculas cadastradas
	 * @return
	 */
	public List<MatriculaCurso> listarMatricula(){
		return this.matriculaRepository.findAll();
	}
	
	/**
	 * Serviço para inserir uma nova matricula
	 * @return
	 */
	public MatriculaCurso cadastrarMatricula(MatriculaCurso matricula) {
		return this.matriculaRepository.save(matricula);
	}
}