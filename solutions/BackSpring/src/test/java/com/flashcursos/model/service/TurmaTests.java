package com.flashcursos.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.entity.Reforco;
import com.flashcursos.model.entity.TurmaReforco;
import com.flashcursos.model.entity.TurmaReforcoAluno;
import com.flashcursos.model.repository.AlunoRepository;
import com.flashcursos.model.repository.ReforcoRepository;

public class TurmaTests extends AbstractIntegrationTests {
	
	@Autowired
	private TurmaService turmaService;

	@Autowired
	private ReforcoRepository reforcoRepository;
	
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
	public void cadastrarTurmaMustPass() {
		TurmaReforco turma = new TurmaReforco();
		
		Reforco reforco = this.reforcoRepository.findById(1001L).orElse(null);
		turma.setReforco(reforco);
		turma.setDataInicio(LocalDateTime.of(2019, 12, 1, 17, 00));
		turma.setDataFim(LocalDateTime.of(2019, 12, 20, 00, 00));
		List<Aluno> alunos = this.alunoRepository.findAll();
		for (Aluno aluno : alunos) {
			TurmaReforcoAluno turmaAluno = new TurmaReforcoAluno();
			turmaAluno.setAluno(aluno);
			turmaAluno.setTurma(turma);
			turma.getAlunosTurma().add(turmaAluno);
		}
		turma.setDisponivel(false);
		
		turmaService.cadastrarTurmaReforco(turma);
		Assert.assertNotNull(turma.getId());
		
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
