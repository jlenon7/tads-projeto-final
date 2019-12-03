package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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
	 * Atributos
	 */
	
	// Services
	@Autowired
	private UsuarioService usuarioService;
	// Repositories
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * ====================================== (CREATE)RUD ===========================================
	 */
	@Test
	@WithUserDetails("jlenon7@hotmail.com")
	@Sql( {	"/dataset/truncate.sql",
		   "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustPass() {
		Usuario usuario = new Usuario();
		usuario.setNome("João Lenon Lopes");
		usuario.setCpf("092.862.989-90");
		usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
		usuario.setCelular("(45) 99955-3219");
		usuario.setEmail("lenonsec7@hotmail.com");
		usuario.setTipousuario(TipoUsuarioEnum.PROFESSOR);
		// VERIFICAR usuario.setSenha("12345");
		this.usuarioService.cadastrarUsuario(usuario);
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
	}
	/**
	 * ====================================== C(READ)UD =============================================
	 */	
	@Test
	@Sql({ "/dataset/truncate.sql",  
		  "/dataset/usuarios.sql"})
	public void listarUsuariosMustPass() {
		List<Usuario> usuarios = this.usuarioService.listarUsuarios();
		Assert.assertEquals(usuarios.size(), 1);
	}	
	/**
	 * ====================================== CR(UPDATE)D ===========================================
	 */	
	@Test
	@Sql({ "/dataset/truncate.sql",  
		"/dataset/usuarios.sql" })
	public void atualizarUsuarioMustPass() {
		Usuario usuario = this.usuarioRepository.findById(1001L).orElse(null);
		usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));

		usuarioService.alterarMinhaConta(usuario, "12345", "12345");

		Assert.assertTrue(usuario.getNascimento().getYear() == 1990);

	}	
	@Test
	@Sql({ "/dataset/truncate.sql",  
		"/dataset/usuarios.sql" })
	public void atualizarUsuarioMustFailSenhaIncorreta() {
		Usuario usuario = this.usuarioRepository.findById(1001L).orElse(null);
		usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));

		usuarioService.alterarMinhaConta(usuario, "1234", "1234");

		Assert.assertTrue(usuario.getNascimento().getYear() == 1990);

	}	
	/**
	 * ====================================== CRU(DELETE) ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql" })
	public void removerUsuarioMustPass() {
		this.usuarioService.removerUsuario(1001);
		Usuario usuario = this.usuarioRepository.findById(1001L).orElse(null);
		Assert.assertNull(usuario);
	}
	
	/* Verificar
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql" })
	public void removerUsuarioMustFailUsuarioNaoEncontrado() {
		this.usuarioService.removerUsuario(1000);
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		Assert.assertNull(usuario);
	} */
		
	// Falta fazer os testes com campos obrigatórios null
	
	/**
	 * ================================== ATIVAR/REDEFINIR/ESQUECI ==================================
	 */
	// ATIVANDO
	@Test
	@WithUserDetails("jlenon7@hotmail.com")
	@Sql( { "/dataset/truncate.sql",
		   "/dataset/usuarios.sql" })
	public void ativarUsuarioMustPass(){
		this.usuarioService.ativarUsuario("12345", "12345", "f786c907-032e-451b-ac93-8508dec75a3d");

		Usuario usuarioAtivo = this.usuarioRepository.findByEmailIgnoreCase("jlenon7@hotmail.com");
		Assert.assertEquals(true, usuarioAtivo.getDisponivel());
	}
	
	// Falta fazer testes com token inválido, token null, token vencido, senha null, senhas diferentes
	
	
	
	
	
	
	// ESQUECI
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
	
	
	
	
	
	
	// REDEFINIR
	@Test()
	@Sql( { "/dataset/truncate.sql",
		   "/dataset/usuarios.sql"
		})
	public void redefinirSenhaMustPass(){
		this.usuarioService.redefinirSenha("123456","123456", "f786c907-032e-451b-ac93-8508dec75a3d");
	}

	//Falta testes sem passar token, com token inválido, com token vencido, com senhas que não conferem, com senha null
}
