package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.repository.ProfessorRepository;

@Service
@Transactional
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	/**
	 * Serviço para listar os professores cadastrados
	 * @return
	 */
	public List<Professor> listarProfessor(){
		return this.professorRepository.findAll();
	}
	
	/**
	 * Serviço para inserir um novo professor
	 * @return
	 */
	public Professor cadastrarProfessor(Professor professor) {
		return this.professorRepository.save(professor);
	}
}
