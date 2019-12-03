package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.repository.ProfessorRepository;
import com.flashcursos.model.service.UsuarioService;

@Service
@Transactional
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	/**
	 * Serviço para inserir um novo professor
	 * 
	 * @param professor
	 * @return
	 */
	public Professor cadastrarProfessor(Professor professor) {
		professor.setTipousuario(TipoUsuarioEnum.PROFESSOR);
		this.usuarioService.cadastrarUsuario(professor);
		return this.professorRepository.save(professor);
	}
	
	/**
	 * Serviço para atualizar o cadastro de um professor
	 * @param professor
	 * @return
	 */
	public Professor atualizarProfessor(Professor professor) {
		return this.professorRepository.save(professor);
	}
	
	/**
	 * Serviço para listar os professores cadastrados
	 * @return
	 */
	public List<Professor> listarProfessores(){
		return this.professorRepository.findAll();
	}
	

	/**
	 * Serviço para detalhar o cadastro de um professor
	 * @param id
	 * @return
	 */
	public Professor detalharProfessor(long id) {
		Professor  professor = this.professorRepository.findById(id).orElse(null);		
		Assert.notNull(professor, "O ID "+ id +" não foi encontrado.");		
		return professor;
	}
	
	/**
	 * Serviço que remove um professor cadastrado
	 * @param id
	 */
	public void removerProfessor(long id) {
		this.professorRepository.deleteById(id);
	}
	
	/**
	 * Serviço que desativa um professor cadastrado
	 * @param id
	 * @return 
	 */
	public Professor desativarProfessor(Professor professor) {
		professor.setDisponivel(false);
		return this.professorRepository.save(professor);
	}

}
