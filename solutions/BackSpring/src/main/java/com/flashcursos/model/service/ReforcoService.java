package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.Reforco;
import com.flashcursos.model.repository.ReforcoRepository;

@Service
@Transactional
public class ReforcoService {
	
	@Autowired
	private ReforcoRepository reforcoRepository;
	
	
	/**
	 * Serviço para inserir um novo reforco
	 * 
	 * @param reforco
	 * @return
	 */
	public Reforco cadastrarReforco(Reforco reforco) {
		reforco.setDisponivel(true);
		return this.reforcoRepository.save(reforco);
	}
	
	/**
	 * Serviço para atualizar o cadastro de um reforco
	 * @param reforco
	 * @return
	 */
	public Reforco atualizarReforco(Reforco reforco) {
		return this.reforcoRepository.save(reforco);
	}
	
	/**
	 * Serviço para listar os reforcos cadastrados
	 * @return
	 */
	public List<Reforco> listarReforcos(){
		return this.reforcoRepository.findAll();
	}
	

	/**
	 * Serviço para detalhar o cadastro de um reforco
	 * @param id
	 * @return
	 */
	public Reforco detalharReforco(long id) {
		
		Reforco  reforco = this.reforcoRepository.findById(id).orElse(null);	
		Assert.notNull(reforco, "O ID "+ id +" não foi encontrado.");	
		return reforco;
	}
	
	/**
	 * Serviço que remove um reforco cadastrado
	 * @param id
	 */
	public void removerReforco(long id) {
		this.reforcoRepository.deleteById(id);
	}
	
	/**
	 * Serviço que desativa um reforço cadastrado
	 * @param id
	 * @return 
	 */
	public Reforco desativarReforco(Reforco reforco) {
		reforco.setDisponivel(false);
		return this.reforcoRepository.save(reforco);
	}
}
