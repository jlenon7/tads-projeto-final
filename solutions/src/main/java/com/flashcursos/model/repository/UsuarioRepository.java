package com.flashcursos.model.repository;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcursos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
<<<<<<< HEAD
	
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
=======
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

}
