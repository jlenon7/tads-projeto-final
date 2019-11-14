package com.flashcursos.model.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.entity.TurmaReforco;
import com.flashcursos.model.repository.AlunoRepository;
import com.flashcursos.model.repository.TurmaRepository;

public class AlunoTests extends AbstractIntegrationTests {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;

	/**
	 * ====================================== (CREATE)RUD ============================================
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
		//TurmaReforco turmareforco = this.turmaRepository.findById(1L).orElse(null);
		//aluno.setTurmareforco(turmareforco);
		
		this.alunoService.cadastrarAluno(aluno);		
		Assert.assertNotNull(aluno);
		Assert.assertNotNull(aluno.getId());		
	}
	
	//Verificar NOTNULL 
	/**
	 * ====================================== C(READ)UD =============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		  "/dataset/turma_reforco.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void listarAlunosMustPass() {
		List<Aluno> aluno = this.alunoService.listarAlunos();
		Assert.assertEquals(aluno.size(), 1);
	}	
	/**
	 * ====================================== CR(UPDATE)D ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		  "/dataset/turma_reforco.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void atualizarAlunoMustPass() {
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);
		aluno.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));

		this.alunoService.atualizarAluno(aluno);
		Assert.assertTrue(aluno.getNascimento().getYear() == 1990);
	}	
	/**
	 * ====================================== CRU(DELETE) ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
		  "/dataset/turma_reforco.sql",
		 "/dataset/usuarios.sql",
		"/dataset/aluno.sql",})
	public void removerAlunoMustPass() {
		this.alunoService.removerAluno(1001);
		Aluno aluno = this.alunoRepository.findById(1001L).orElse(null);
		Assert.assertNull(aluno);
	}
}
