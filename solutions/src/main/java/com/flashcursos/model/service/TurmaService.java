package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcursos.model.entity.TurmaReforco;
import com.flashcursos.model.repository.TurmaRepository;

@Service
@Transactional
public class TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	/**
	 * Serviço para listar os turmas cadastradas
	 * @return
	 */
	public List<TurmaReforco> listarTurmaReforco(){
		return this.turmaRepository.findAll();
	}
	
	/**
	 * Serviço para inserir uma nova turma
	 * @return
	 */
	public TurmaReforco cadastrarTurmaReforco(TurmaReforco turma) {
		return this.turmaRepository.save(turma);
	}
}