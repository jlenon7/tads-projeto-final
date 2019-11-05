package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * ====================================== (CREATE)RUD ============================================
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
	/**
	 * ====================================== CRU(DELETE) ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/professor.sql"})
	public void removerUsuarioMustPass() {
		this.professorService.removerProfessor(1001);
		Professor professor = this.professorRepository.findById(1001L).orElse(null);
		Assert.assertNull(professor);
	}
}
