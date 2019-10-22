package br.com.lenonsec.flashcursos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lenonsec.flashcursos.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	/*
	 * 
	 */
	Usuario findByEmailIgnoreCase(String email);
	
	/**
	 *
	 */
	Optional<Usuario> findByPasswordResetToken( String token );
	
	/**
	 *
	 */
	Optional<Usuario> findByAccountActivateToken( String token );
	
}
