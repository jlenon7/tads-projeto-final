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
	 * ====================================== LISTAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql", 
		 "/dataset/professor.sql" })
	public void listarProfessoresMustPass() {
		List<Professor> professor = this.professorService.listarProfessor();
		Assert.assertEquals(professor.size(), 2);

	}

	/**
	 * ====================================== CADASTRAR ===========================================
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
		professor.setSenha("daledeledeledolly");
		professor.setCelular("(45) 99955-3219");
		professor.setTipousuario(TipoUsuarioEnum.PROFESSOR);
		professor.setDisponivel(true);

		professor.setAreaConhecimento(AreaConhecimentoEnum.DEV_MOBILE);
		
		professorService.cadastrarProfessor(professor);

		Assert.assertNotNull(professor.getId());
		
	}
}
