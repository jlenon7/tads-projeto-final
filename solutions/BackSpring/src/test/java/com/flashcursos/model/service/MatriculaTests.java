package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.entity.Curso;
import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.repository.AlunoRepository;
import com.flashcursos.model.repository.CursoRepository;

public class MatriculaTests extends AbstractIntegrationTests {
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	/**
	 * ====================================== (CREATE)RUD ============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql",
		 "/dataset/aluno.sql",
		"/dataset/professor.sql", 
	   "/dataset/reforcos.sql" })
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
		matricula.setDisponivel(true);
		
	}
	
	/**
	 * ======================================= C(READ)UD =============================================
	 */
	/**
	 * ======================================= CR(UPDATE)D ===========================================
	 */
	/**
	 * ======================================= CRU(DELETE) ===========================================
	 */

}
