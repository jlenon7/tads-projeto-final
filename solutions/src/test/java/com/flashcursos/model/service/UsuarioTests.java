package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.entity.Usuario;
import com.flashcursos.model.repository.UsuarioRepository;
import com.flashcursos.model.service.UsuarioService;

public class UsuarioTests extends AbstractIntegrationTests {

	/*
	 * Atributos 👍😂
	 */
	
	// Services
	@Autowired
	private UsuarioService usuarioService;
	// Repositories
	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * Tests 😎💪🏻
	 */
	
	// Cadastrar um Usuário 🥳🖐
		@Test
		@WithUserDetails("jlenon7@hotmail.com")
		@Sql( {	"/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void cadastrarUsuarioMustPass()
		{
			Usuario usuario = new Usuario();
			usuario.setNome("João Lenon Lopes");
			usuario.setCpf("092.862.989-90");
			usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
			usuario.setCelular("(45) 99955-3219");
			usuario.setEmail("lenonsec7@mailinator.com");
			usuario.setTipousuario(TipoUsuarioEnum.PROFESSOR);
			this.usuarioService.cadastrarUsuario(usuario);
			Assert.assertNotNull(usuario);
			Assert.assertNotNull(usuario.getId());

		}
		
		// Falta fazer os testes com campos obrigatórios null
		
	// Ativando o usuário 🥳🖐
		@Test
		@WithUserDetails("jlenon7@hotmail.com")
		@Sql( { "/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void ativarUsuarioMustPass(){
			this.usuarioService.ativarUsuario("123456", "123456", "f786c907-032e-451b-ac93-8508dec75a3d");

			Usuario usuarioAtivo = this.usuarioRepository.findByEmailIgnoreCase("jlenon7@hotmail.com");
			Assert.assertEquals(true, usuarioAtivo.getDisponivel());
		}
		
		// Falta fazer testes com token inválido, token null, token vencido, senha null, senhas diferentes
		
	// Esqueci minha senha 🥳🖐
		@Test
		@Sql( { "/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void enviarEmailRecuperarSenhaUsuarioMustPass(){
			this.usuarioService.enviarEmailRecuperarSenhaUsuario("jlenon7@hotmail.com");
			Usuario usuario = this.usuarioRepository.findByEmailIgnoreCase( "jlenon7@hotmail.com" );
			
			Assert.assertNotNull( usuario);
			Assert.assertNotNull( usuario.getPasswordResetToken());
			Assert.assertNotNull( usuario.getPasswordResetTokenExpiration());
		}
		
		//Faltou testes com email que não existe e sem passar email
		
	// Redefinir minha senha 🥳🖐
		@Test()
		@Sql( { "/dataset/truncate.sql",
			   "/dataset/usuarios.sql"
			})
		public void redefinirSenhaMustPass(){
			this.usuarioService.redefinirSenha("123456","123456", "f786c907-032e-451b-ac93-8508dec75a3d");
		}

		//Falta testes sem passar token, com token inválido, com token vencido, com senhas que não conferem, com senha null

	// Pegar usuário autenticado 🥳🖐
		@Test
		@WithUserDetails("jlenon7@hotmail.com")
		@Sql({ "/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void pegarUsuarioAutenticadoTestMustPass() {
			Usuario usuario = this.usuarioService.getAuthenticatedUser();
			Assert.assertNotNull(usuario);
			Assert.assertNotNull(usuario.getId());
			
		}
	// Alterar minha conta 🥳🖐
		
	// Remover usuário 🥳🖐
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql" })
	public void removerUsuarioMustPass() {
		this.usuarioService.removerUsuario(1000);
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		Assert.assertNull(usuario);
	}
	

}
