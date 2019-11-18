package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.repository.MatriculaRepository;

@Service
@Transactional
public class MatriculaService {
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	
	/**
	 * Serviço para inserir uma nova matricula
	 * 
	 * @param matricula
	 * @return
	 */
	public MatriculaCurso cadastrarMatriculaCurso(MatriculaCurso matricula) {
		return this.matriculaRepository.save(matricula);
	}
	
	/**
	 * Serviço para atualizar o cadastro de uma matricula
	 * @param matricula
	 * @return
	 */
	public MatriculaCurso atualizarMatriculaCurso(MatriculaCurso matricula) {
		return this.matriculaRepository.save(matricula);
	}
	
	/**
	 * Serviço para listar as matriculas cadastradas
	 * @return
	 */
	public List<MatriculaCurso> listarMatriculaCursos(){
		return this.matriculaRepository.findAll();
	}
	

	/**
	 * Serviço para detalhar o cadastro de uma matricula
	 * @param id
	 * @return
	 */
	public MatriculaCurso detalharMatriculaCurso(long id) {
		MatriculaCurso  matricula = this.matriculaRepository.findById(id).orElse(null);
		Assert.notNull(matricula, "O ID "+ id +" não foi encontrado.");
		return matricula;
	}
	
	/**
	 * Serviço que remove uma matricula cadastrada
	 * @param id
	 */
	public void removerMatriculaCurso(long id) {
		this.matriculaRepository.deleteById(id);
	}
}