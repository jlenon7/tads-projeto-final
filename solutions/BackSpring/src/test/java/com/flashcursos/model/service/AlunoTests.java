package com.flashcursos.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.repository.AlunoRepository;

public class AlunoTests extends AbstractIntegrationTests {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;

	/**
	 * ====================================== (CREATE)RUD ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",})
	public void cadastrarAlunoMustPass() {
		Aluno aluno = new Aluno();

		aluno.setNome("João Lenon Lopes");
		aluno.setCpf("092.862.989-90");
		aluno.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
		aluno.setEmail("lenonsec7@gmail.com");
		aluno.setCelular("(45) 99955-3219");
		aluno.setTipousuario(TipoUsuarioEnum.ALUNO);
		
		this.alunoService.cadastrarAluno(aluno);		
		Assert.assertNotNull(aluno);
		Assert.assertNotNull(aluno.getId());		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void cadastrarAlunoMustFailCpfDuplicado() {
		Aluno aluno = new Aluno();
		aluno.setNome("Adryell");
		aluno.setEmail("adryell.silva10@gmail.com");
		aluno.setCpf("092.862.989-94");
		aluno.setNascimento(LocalDate.of(1995, Month.JANUARY, 1));
		aluno.setCelular("478597-2577");

		this.alunoService.cadastrarAluno(aluno);
	}
	
	@Test(expected = ValidationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void cadastrarAlunoMustFailNomeEmBranco() {
		Aluno aluno = new Aluno();
		aluno.setNome("");
		aluno.setEmail("adryell.silva10@gmail.com");
		aluno.setCpf("345.862.989-94");
		aluno.setNascimento(LocalDate.of(1995, Month.JANUARY, 1));
		aluno.setCelular("478597-2577");

		this.alunoService.cadastrarAluno(aluno);
	}
	//Verificar NOTNULL 
	/**
	 * ====================================== C(READ)UD =============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void listarAlunosMustPass() {
		List<Aluno> aluno = this.alunoService.listarAlunos();
		Assert.assertEquals(aluno.size(), 2);
	}	
	
	/* Rever este teste
	@Test
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void listarAlunosPorFiltrosMustPassFiltrarPorNome() {
		List<Aluno> alunos = this.alunoService.listarAlunosPorFiltros("João", null, null).getContent();
		Assert.assertEquals(1, alunos.size());
	}
	*/
	
	/**
	 * ====================================== CR(UPDATE)D ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void atualizarAlunoMustPass() {
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);
		aluno.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));

		this.alunoService.atualizarAluno(aluno);
		Assert.assertTrue(aluno.getNascimento().getYear() == 1990);
	}	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void atualizarAlunoMustFailCpfDuplicado() {
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);

		aluno.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
		aluno.setCpf("116.506.789-75");

		alunoService.atualizarAluno(aluno);
	}
	
	@Test(expected = ValidationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void atualizarAlunoMustFailNomeEmBranco() {
		Aluno aluno = new Aluno();
		aluno.setNome("");
		aluno.setCelular("45 999964523");
		aluno.setCpf("64444444444");
		aluno.setEmail("adryell.silva10@gmail.com");

		this.alunoService.atualizarAluno(aluno);
	}
	/**
	 * ====================================== CRU(DELETE) ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void removerAlunoMustPass() {
		this.alunoService.removerAluno(1001);
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);
		Assert.assertNull(aluno);
	}
	
	/**
	 * ================== DETALHAR ===============================
	 */
	@Test()
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void detalharAlunoMustPass() {
		Aluno aluno = this.alunoService.detalharAluno(1001L);

		Assert.assertNotNull(aluno);
		Assert.assertNotNull(aluno.getId());
		Assert.assertEquals(aluno.getCpf(), "092.862.989-94");
	}

	@Test(expected = IllegalArgumentException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/aluno.sql" })
	public void detalharAlunoMustFailIdNaoExiste() {
		Aluno aluno = this.alunoService.detalharAluno(1L);
	}
	
	/**
	 * ====================================== DESATIVAR ============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void desativarAlunoMustPass() {
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);
		
		this.alunoService.desativarAluno(aluno);
		Assert.assertNotNull(aluno.getId());
	}
	
}
