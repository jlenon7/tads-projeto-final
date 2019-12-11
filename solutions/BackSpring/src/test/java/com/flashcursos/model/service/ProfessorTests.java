package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.AreaConhecimentoEnum;
import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.repository.ProfessorRepository;

public class ProfessorTests extends AbstractIntegrationTests {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorRepository professorRepository;

	/**
	 * ====================================== (CREATE)RUD ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/professor.sql" })

	public void cadastrarProfessorMustPass() {
		Professor professor = new Professor();

		professor.setNome("João Lenon Lopes");
		professor.setCpf("092.862.989-90");
		professor.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
		professor.setEmail("lenonsec7@gmail.com");
		professor.setCelular("(45) 99955-3219");
		professor.setTipousuario(TipoUsuarioEnum.PROFESSOR);
		professor.setAreaConhecimento(AreaConhecimentoEnum.DEV_MOBILE);
		
		this.professorService.cadastrarProfessor(professor);		
		Assert.assertNotNull(professor);
		Assert.assertNotNull(professor.getId());		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void cadastrarProfessorMustFailCpfDuplicado() {
		Professor professor = new Professor();
		professor.setNome("Adryell");
		professor.setEmail("adryell.silva10@gmail.com");
		professor.setCpf("092.862.989-94");
		professor.setNascimento(LocalDate.of(1995, Month.JANUARY, 1));
		professor.setCelular("478597-2577");

		this.professorService.cadastrarProfessor(professor);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void cadastrarProfessorMustFailEmailDuplicado() {
		Professor professor = new Professor();
		
		professor.setNome("Adryell");
		professor.setEmail("jlenon7@hotmail.com");
		professor.setCpf("123.456.789-10");
		professor.setNascimento(LocalDate.of(1995, Month.JANUARY, 1));
		professor.setCelular("478597-2577");
		
		this.professorService.cadastrarProfessor(professor);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void cadastrarProfessorMustFailTelefoneDuplicado() {
		Professor professor = new Professor();
		
		professor.setNome("Adryell");
		professor.setEmail("adryell.silva10@gmail.com");
		professor.setCpf("123.456.789-10");
		professor.setNascimento(LocalDate.of(1995, Month.JANUARY, 1));
		professor.setCelular("(45) 99955-3220");
		
		this.professorService.cadastrarProfessor(professor);
	}
	
	@Test(expected = ValidationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void cadastrarProfessorMustFailNomeEmBranco() {
		Professor professor = new Professor();
		professor.setNome("");
		professor.setEmail("adryell.silva10@gmail.com");
		professor.setCpf("345.862.989-94");
		professor.setNascimento(LocalDate.of(1995, Month.JANUARY, 1));
		professor.setCelular("478597-2577");

		this.professorService.cadastrarProfessor(professor);
	}
	
	/**
	 * ====================================== C(READ)UD =============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql", 
		 "/dataset/professor.sql" })
	public void listarProfessoresMustPass() {
		List<Professor> professor = this.professorService.listarProfessores();
		Assert.assertEquals(professor.size(), 1);
	}	
	/**
	 * ====================================== CR(UPDATE)D ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",  
		  "/dataset/usuarios.sql",
		 "/dataset/professor.sql"})
	public void atualizarProfessorMustPass() {
		Professor professor = this.professorRepository.findById(1001L).orElse(null);
		professor.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));

		this.professorService.atualizarProfessor(professor);
		Assert.assertTrue(professor.getNascimento().getYear() == 1990);
	}	
	

	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void atualizarProfessorMustFailCpfDuplicado() {
		Professor professor = this.professorRepository.findById(1001L).orElse(null);

		professor.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
		professor.setCpf("092.862.989-94");

		professorService.atualizarProfessor(professor);

	}

	@Test(expected = ValidationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void atualizarProfessorMustFailNomeEmBranco() {
		Professor professor = new Professor();
		professor.setNome("");
		professor.setCpf("007.574.236-85");
		professor.setNascimento(LocalDate.of(2000, Month.JANUARY, 1));

		this.professorService.atualizarProfessor(professor);
	}
	/**
	 * ====================================== CRU(DELETE) ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/professor.sql"})
	public void removerProfessorMustPass() {
		this.professorService.removerProfessor(1001);
		Professor professor = this.professorRepository.findById(1001L).orElse(null);
		Assert.assertNull(professor);
	}
	
	/**
	 * ================== DETALHAR ===============================
	 */
	@Test()
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void detalharProfessorMustPass() {
		Professor professor = this.professorService.detalharProfessor(1001L);

		Assert.assertNotNull(professor);
		Assert.assertNotNull(professor.getId());
		Assert.assertEquals(professor.getCpf(), "092.862.989-94");
	}

	@Test(expected = IllegalArgumentException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql" })
	public void detalharAlunoMustFailIdNaoExiste() {
		this.professorService.detalharProfessor(1L);
	}
	
	/**
	 * ====================================== DESATIVAR ============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		 "/dataset/usuarios.sql",
		"/dataset/professor.sql",})
	public void desativarProfessorMustPass() {
		Professor professor = this.professorRepository.findById(1001L).orElse(null);
		
		this.professorService.desativarProfessor(professor);
		Assert.assertNotNull(professor.getId());
	}
}
