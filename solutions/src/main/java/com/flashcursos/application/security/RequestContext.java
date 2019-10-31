package com.flashcursos.application.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

import com.flashcursos.model.entity.Usuario;

/**
 *
 */
public abstract class RequestContext
{
	/*-------------------------------------------------------------------
	 * 		 						BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @return
	 */
	public static Optional<Usuario> currentUser()
	{
		return Optional.ofNullable( SecurityContextHolder.getContext().getAuthentication() )
				.map( auth -> auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null );
	}


}
