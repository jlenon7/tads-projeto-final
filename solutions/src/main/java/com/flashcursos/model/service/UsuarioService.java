package com.flashcursos.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.Usuario;
import com.flashcursos.model.repository.UsuarioRepository;


@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	/**
	 * Serviço para inserir um novo usuario
	 * 
	 * @param aluno
	 * @return
	 */
	public Usuario cadastrarUsuario(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	/**
	 * Serviço para atualizar o cadastro de um usuario
	 * @param funcionario
	 * @return
	 */
	public Usuario atualizarUsuario(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	/**
	 * Serviço para listar os usuarios cadastrados
	 * @return
	 */
	public List<Usuario> listarUsuarios(){
		return this.usuarioRepository.findAll();
	}
	

	/**
	 * Serviço para detalhar o cadastro de um usuario
	 * @param id
	 * @return
	 */
	public Usuario detalharUsuario(long id) {
		Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
		
		Assert.notNull(usuario, "ID "+ id +" não encontrado!");
		
		return usuario;
	}
	/**
	 * Serviço para remover o cadastro de um usuario
	 * @param id
	 * @return
	 */
	public void removerUsuario(long id) {
		this.usuarioRepository.deleteById(id);
	}
}
