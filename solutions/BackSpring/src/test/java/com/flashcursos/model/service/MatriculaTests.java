package com.flashcursos.model.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.entity.Curso;
import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.repository.AlunoRepository;
import com.flashcursos.model.repository.CursoRepository;
import com.flashcursos.model.repository.MatriculaRepository;

public class MatriculaTests extends AbstractIntegrationTests {
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	/**
	 * ====================================== (CREATE)RUD ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",
		"/dataset/professor.sql", 
	   "/dataset/cursos.sql",
	  "/dataset/matricula_curso.sql"})
	public void cadastrarMatriculaMustPass() {
		MatriculaCurso matricula = new MatriculaCurso();
		
		Curso curso = this.cursoRepository.findById(1001L).orElse(null);
		matricula.setCurso(curso);
		
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);
		matricula.setAluno(aluno);
		
		LocalDateTime dataMatricula = (LocalDateTime.now());
		matricula.setDataMatricula(dataMatricula);
		LocalDateTime daquiUmAno = dataMatricula.plus(1, ChronoUnit.YEARS);
		
		System.out.println(daquiUmAno);
		
		matricula.setDataVencimentoMatricula(daquiUmAno);	
		
		this.matriculaService.cadastrarMatricula(matricula);		
		Assert.assertNotNull(matricula);
		Assert.assertNotNull(matricula.getId());
		
	}
	
	
	/**
	 * ======================================= C(READ)UD =============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",
		"/dataset/professor.sql", 
	   "/dataset/cursos.sql",
      "/dataset/matricula_curso.sql"})
	public void listarMatriculasMustPass() {
		List<MatriculaCurso> matriculas = this.matriculaService.listarMatricula();
		Assert.assertEquals(matriculas.size(), 2);
	}	
	/**
	 * ======================================= CR(UPDATE)D ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",
		"/dataset/professor.sql", 
	   "/dataset/cursos.sql",
	  "/dataset/matricula_curso.sql"})
	
	public void atualizarMatriculaMustPass() {
		MatriculaCurso matricula = this.matriculaRepository.findById(1001L).orElse(null);
		matricula.setDataVencimentoMatricula(LocalDateTime.of(2021, 12, 1, 2, 30));

		this.matriculaService.atualizarMatricula(matricula);
		Assert.assertTrue(matricula.getDataVencimentoMatricula().getYear() == 2021);
	}
	/**
	 * ======================================= CRU(DELETE) ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",
		"/dataset/professor.sql", 
	   "/dataset/cursos.sql",
	  "/dataset/matricula_curso.sql"})
	public void removerMatriculaMustPass() {
		this.matriculaService.removerMatricula(1001);
		MatriculaCurso matricula = this.matriculaRepository.findById(1001L).orElse(null);
		Assert.assertNull(matricula);
	}
	/**
	 * ====================================== DESATIVAR ============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",
		"/dataset/professor.sql", 
	   "/dataset/cursos.sql",
	  "/dataset/matricula_curso.sql"})
	public void desativarMatriculaMustPass() {
		MatriculaCurso matricula = this.matriculaRepository.findById(1001L).orElse(null);
		
		this.matriculaService.desativarMatricula(matricula);
		Assert.assertNotNull(matricula.getId());
	}

}
