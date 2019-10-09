package com.flashcursos.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.entity.Usuario;
import com.flashcursos.model.repository.UsuarioRepository;
import com.flashcursos.model.service.UsuarioService;

public class UsuarioTests extends AbstractIntegrationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * ====================================== LISTAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void listarUsuariosMustPass() {
		List<Usuario> usuarios = this.usuarioService.listarUsuarios();
		Assert.assertEquals(usuarios.size(), 2);

	}

	/**
	 * ====================================== CADASTRAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustPass() {
		Usuario usuario = new Usuario();
		usuario.setEmail("jlenon7@gmail.com");
		usuario.setSenha("12345");
		usuario.setCpf("092.862.988-93");
		usuario.setCelular("");
		usuario.setDisponivel(true);
		usuario.setTipousuario(TipoUsuarioEnum.ADMIN);

		usuarioService.cadastrarUsuario(usuario);

		Assert.assertNotNull(usuario.getId());

	}

	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustFailEmailDuplicado() {
		Usuario usuario = new Usuario();
		usuario.setEmail("jlenon7@hotmail.com");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ALUNO);

		usuarioService.cadastrarUsuario(usuario);

	}

	@Test(expected = ConstraintViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustFailEmailEmBranco() {
		Usuario usuario = new Usuario();
		usuario.setEmail("");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ALUNO);

		this.usuarioService.cadastrarUsuario(usuario);
	}

	/**
	 * 
	 * ======================== ATUALIZAR ============================
	 */

	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void atualizarUsuarioMustPass() {
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		usuario.setEmail("lenonsec7@hotmail.com");
		usuario.setSenha("123456");
		usuario.setTipousuario(TipoUsuarioEnum.PROFESSOR);

		usuarioService.atualizarUsuario(usuario);

	}

	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void atualizarUsuarioMustFailEmailDuplicado() {
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		usuario.setEmail("jlenon7@hotmail.com");
		usuario.setSenha("1234567");
		usuario.setTipousuario(TipoUsuarioEnum.ADMIN);
	
		usuarioService.atualizarUsuario(usuario);

	}

	@Test(expected = ConstraintViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void atualizarUsuarioMustFailEmailEmBranco() {
		Usuario usuario = new Usuario();
		usuario.setEmail("");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ADMIN);

		this.usuarioService.cadastrarUsuario(usuario);
	}

	/**
	 * ================== DETALHAR ===============================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void detalharUsuarioMustPass() {
		Usuario usuario = this.usuarioService.detalharUsuario(1000L);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
		Assert.assertEquals(usuario.getEmail(), "jlenon7@hotmail.com");

	}
	@Test(expected = IllegalArgumentException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void detalharUsuarioMustFailIdNaoExiste() {
		Usuario usuario = this.usuarioService.detalharUsuario(1L);
		
	}
	/**
	 * =================== EXCLUIR ===============================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void removerUsuarioMustPass() {
		this.usuarioService.removerUsuario(1000);
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		Assert.assertNull(usuario);
	}
	

}
