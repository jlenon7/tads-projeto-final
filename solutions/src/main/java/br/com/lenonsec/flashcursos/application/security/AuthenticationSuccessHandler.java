package br.com.lenonsec.flashcursos.application.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lenonsec.flashcursos.entidades.Usuario;
import br.com.lenonsec.flashcursos.repository.UsuarioRepository;

@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler
{
	/**
	 *
	 */
	private static final Logger LOG = Logger.getLogger( AuthenticationSuccessHandler.class.getName() );
	
	/*-------------------------------------------------------------------
	 * 		 					 ATTRIBUTES
	 *-------------------------------------------------------------------*/
	//Repositories
	/**
	 *
	 */
	private final UsuarioRepository usuarioRepository;

	private final ObjectMapper objectMapper;

	@Autowired
	public AuthenticationSuccessHandler( UsuarioRepository usuarioRepository, ObjectMapper objectMapper )
	{
		this.usuarioRepository = usuarioRepository;
		this.objectMapper = objectMapper;
	}

	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException
	{
		try
		{
			final Usuario user = RequestContext.currentUser().map( Usuario::getId ).flatMap( this.usuarioRepository::findById ).orElseThrow( () -> new IllegalArgumentException( "Ocorreu um problema ao atualizar o ultimo login do usuário" ) );
			this.usuarioRepository.save( user );
			this.objectMapper.writeValue( response.getWriter(), user );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			LOG.severe( "Ocorreu um problema ao atualizar o ultimo login do usuário" );
		}
	}
}
