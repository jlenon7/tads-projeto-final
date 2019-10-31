package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcursos.model.entity.Reforco;
import com.flashcursos.model.repository.ReforcoRepository;

@Service
@Transactional
public class ReforcoService {
	
	@Autowired
	private ReforcoRepository reforcoRepository;
	
	/**
	 * Serviço para listar os reforcos cadastrados
	 * @return
	 */
	public List<Reforco> listarReforcos(){
		return this.reforcoRepository.findAll();
	}
	
	/**
	 * Serviço para inserir um novo reforço
	 * @return
	 */
	public Reforco cadastrarReforco(Reforco reforco) {
		return this.reforcoRepository.save(reforco);
	}

}
