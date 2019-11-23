package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.repository.AlunoRepository;

@Service
@Transactional
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Serviço para inserir um novo aluno
	 * 
	 * @param aluno
	 * @return
	 */
	public Aluno cadastrarAluno(Aluno aluno) {
		this.usuarioService.cadastrarUsuario(aluno);
		return this.alunoRepository.save(aluno);
	}
	
	/**
	 * Serviço para atualizar o cadastro de um aluno
	 * @param aluno
	 * @return
	 */
	public Aluno atualizarAluno(Aluno aluno) {
		return this.alunoRepository.save(aluno);
	}
	
	/**
	 * Serviço para listar os alunos cadastrados
	 * @return
	 */
	public List<Aluno> listarAlunos(){
		return this.alunoRepository.findAll();
	}
	

	/**
	 * Serviço para detalhar o cadastro de um aluno
	 * @param id
	 * @return
	 */
	public Aluno detalharAluno(long id) {	
		Aluno  aluno = this.alunoRepository.findById(id).orElse(null);	
		Assert.notNull(aluno, "O ID "+ id +" não foi encontrado.");
		return aluno;
	}
	
	/**
	 * Serviço que remove um aluno cadastrado
	 * @param id
	 */
	public void removerAluno(long id) {
		this.alunoRepository.deleteById(id);
	}
	
	/**
	 * Serviço que desativa um aluno cadastrado
	 * @param id
	 * @return 
	 */
	public Aluno desativarAluno(Aluno aluno) {
		aluno.setDisponivel(false);
		return this.alunoRepository.save(aluno);
	}
}