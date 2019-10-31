package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.repository.AlunoRepository;

@Service
@Transactional
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;

	/**
	 * Serviço para listar os alunos cadastrados
	 * @return
	 */
	public List<Aluno> listarAluno(){
		return this.alunoRepository.findAll();
	}
	
	/**
	 * Serviço para inserir um novo aluno
	 * @return
	 */
	public Aluno cadastrarAluno(Aluno aluno) {
		return this.alunoRepository.save(aluno);
	}
}