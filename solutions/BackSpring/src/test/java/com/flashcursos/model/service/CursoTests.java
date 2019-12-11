package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.repository.CursoRepository;
import com.flashcursos.model.repository.ProfessorRepository;
import com.flashcursos.model.entity.Curso;
import com.flashcursos.model.entity.DificuldadeEnum;

public class CursoTests extends AbstractIntegrationTests {
	
	@Autowired
	private CursoService cursoService;

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	/**
	 * ====================================== (CREATE)RUD ===========================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql", 
			"/dataset/usuarios.sql", 
			"/dataset/professor.sql", 
			"/dataset/cursos.sql" })
	public void cadastrarCursoMustPass() {
		Curso curso = new Curso();
		
		curso.setArea("Spring Boot - Intermediário");
		curso.setCargaHoraria(100);
		curso.setDificuldade(DificuldadeEnum.INTERMEDIARIO);
		Professor ministrante = this.professorRepository.findById(1001L).orElse(null);
		curso.setMinistrante(ministrante);
		
		this.cursoService.cadastrarCurso(curso);
		Assert.assertNotNull(curso.getId());
		
	}
	
	@Test(expected = ValidationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql",
		"/dataset/cursos.sql "})
	public void cadastrarCursoMustFailAreaEmBranco() {
		Curso curso = new Curso();
		
		curso.setArea("");
		curso.setCargaHoraria(100);
		curso.setDificuldade(DificuldadeEnum.INTERMEDIARIO);
		Professor ministrante = this.professorRepository.findById(1001L).orElse(null);
		curso.setMinistrante(ministrante);

		this.cursoService.cadastrarCurso(curso);
	}
	
	/**
	 * ====================================== C(READ)UD =============================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/cursos.sql" })
	public void listarCursosMustPass() {
		List<Curso> cursos = this.cursoService.listarCursos();
		Assert.assertEquals(cursos.size(), 1);
		
	}
	
	/**
	 * ====================================== CR(UPDATE)D ===========================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/cursos.sql" })
	public void alterarCursoMustPass() {
		Curso curso = this.cursoRepository.findById(1001L).orElse(null);
		
		curso.setArea("Spring Boot - Avançado");
		curso.setUpdated(LocalDateTime.now());
		curso.setCargaHoraria(300);
		curso.setDificuldade(DificuldadeEnum.AVANCADO);
		
		this.cursoService.atualizarCurso(curso);
	}
	
	@Test(expected = ValidationException.class)
	@Sql({ "/dataset/truncate.sql", 
		"/dataset/usuarios.sql", 
		"/dataset/professor.sql",
		"/dataset/cursos.sql "})
	public void atualizarCursoMustFailNomeEmBranco() {
		Curso curso = new Curso();
		
		curso.setArea("");
		curso.setCargaHoraria(100);
		curso.setDificuldade(DificuldadeEnum.INTERMEDIARIO);
		Professor ministrante = this.professorRepository.findById(1001L).orElse(null);
		curso.setMinistrante(ministrante);

		this.cursoService.atualizarCurso(curso);
	}

	
	/**
	 * ====================================== CRU(DELETE) ===========================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/cursos.sql" })
	public void excluirCursoMustPass() {
		this.cursoService.removerCurso(1001);
		Curso curso = this.cursoRepository.findById(1001L).orElse(null);
		Assert.assertNull(curso);
	}
	/**
	 * ====================================== DESATIVAR ============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/cursos.sql" })
	public void desativarCursoMustPass() {
		Curso curso = this.cursoRepository.findById(1001L).orElse(null);
		
		this.cursoService.desativarCurso(curso);
		Assert.assertNotNull(curso.getId());
	}
}
