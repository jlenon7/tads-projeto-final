package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.TurmaReforco;
import com.flashcursos.model.repository.TurmaRepository;

@Service
@Transactional
public class TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	
	/**
	 * Serviço para inserir uma nova turma
	 * 
	 * @param turma
	 * @return
	 */
	public TurmaReforco cadastrarTurmaReforco(TurmaReforco turma) {
		return this.turmaRepository.save(turma);
	}
	
	/**
	 * Serviço para atualizar o cadastro de uma turma
	 * @param turma
	 * @return
	 */
	public TurmaReforco atualizarTurmaReforco(TurmaReforco turma) {
		return this.turmaRepository.save(turma);
	}
	
	/**
	 * Serviço para listar as turmas cadastradas
	 * @return
	 */
	public List<TurmaReforco> listarTurmaReforcos(){
		return this.turmaRepository.findAll();
	}

	/**
	 * Serviço para detalhar o cadastro de uma turma
	 * @param id
	 * @return
	 */
	public TurmaReforco detalharTurmaReforco(long id) {
		TurmaReforco  turma = this.turmaRepository.findById(id).orElse(null);
		Assert.notNull(turma, "O ID "+ id +" não foi encontrado.");
		return turma;
	}
	
	/**
	 * Serviço que remove uma turma cadastrada
	 * @param id
	 */
	public void removerTurmaReforco(long id) {
		this.turmaRepository.deleteById(id);
	}
}