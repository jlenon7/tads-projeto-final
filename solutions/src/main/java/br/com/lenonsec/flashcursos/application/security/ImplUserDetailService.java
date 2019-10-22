package br.com.lenonsec.flashcursos.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.lenonsec.flashcursos.entidades.Usuario;
import br.com.lenonsec.flashcursos.repository.UsuarioRepository;

@Service
@Transactional
public class ImplUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email)  {
		Usuario usuario = this.repository.findByEmailIgnoreCase(email);
		Assert.notNull(usuario, "Nenhum usuário encontrado com o e-mail especificado.");
		return usuario;
	}	

}
