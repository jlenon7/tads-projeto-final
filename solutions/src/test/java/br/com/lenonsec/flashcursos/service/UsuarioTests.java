package br.com.lenonsec.flashcursos.service;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.lenonsec.flashcursos.entidades.TipoUsuarioEnum;
import br.com.lenonsec.flashcursos.entidades.Usuario;
import br.com.lenonsec.flashcursos.repository.UsuarioRepository;
import br.com.lenonsec.flashcursos.services.UsuarioService;

public class UsuarioTests extends AbstractIntegrationTests {
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/**
	 * Password encoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 *
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 */
	@Autowired
	private UsuarioService usuarioService;
	
	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/
	
	//======== CADASTRAR USUÁRIO =============

		/**
		 *
		 */
		@Test
		@WithUserDetails("marcieli.langer@mailinator.com")
		@Sql( {		    
		      "/dataset/truncate.sql",
					"/dataset/usuarios.sql"
			})
		public void cadastrarUsuarioMustPass()
		{
			Usuario usuario = new Usuario();
			usuario.setNome("João Lenon Lopes");
			usuario.setCpf("092.862.989-90");
			usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
			usuario.setEmail( "joaozinho35gamer@hotmail.com" );
			usuario.setSenha("daledeledeledolly");
			usuario.setCelular("(45) 99955-3219");
			usuario.setTipoUsuario( TipoUsuarioEnum.ALUNO );
			usuario.setDisponivel(true);
			this.usuarioService.cadastrarUsuario(usuario);
			Assert.assertNotNull( usuario );
			Assert.assertNotNull( usuario.getId() );

		}
}
