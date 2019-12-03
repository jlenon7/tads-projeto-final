package com.flashcursos.model.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.flashcursos.model.entity.DisciplinaEnum;
import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.entity.Reforco;
import com.flashcursos.model.repository.ProfessorRepository;
import com.flashcursos.model.repository.ReforcoRepository;

public class ReforcoTests extends AbstractIntegrationTests {

	@Autowired
	private ReforcoService reforcoService;

	@Autowired
	private ReforcoRepository reforcoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;


	/**
	 * ====================================== (CREATE)RUD ============================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql", 
			"/dataset/usuarios.sql", 
			"/dataset/professor.sql", 
			"/dataset/reforcos.sql" })
	public void cadastrarReforcoMustPass() {
		Reforco reforco = new Reforco();
		
		reforco.setDisciplina(DisciplinaEnum.MATEMATICA);
		reforco.setArea("Funções Matemáticas");
		reforco.setHoraInicio(LocalDateTime.of(2019, Month.DECEMBER, 01, 17, 00));
		reforco.setVagas(10);
		Professor ministrante = this.professorRepository.findById(1001L).orElse(null);
		reforco.setMinistrante(ministrante);
		
		reforcoService.cadastrarReforco(reforco);
		Assert.assertNotNull(reforco.getId());	
	}	
	/**
	 * ======================================= C(READ)UD =============================================
	 */	
	@Test
	@Sql({ "/dataset/truncate.sql",
		"/dataset/usuarios.sql",
		"/dataset/professor.sql",
		"/dataset/reforcos.sql" })
	public void listarReforcosMustPass() {
		List<Reforco> reforcos = this.reforcoService.listarReforcos();
		Assert.assertEquals(reforcos.size(), 2);

	}	
	/**
	 * ======================================= CR(UPDATE)D ===========================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/reforcos.sql" })
	public void alterarReforcoMustPass() {
		Reforco reforco = this.reforcoRepository.findById(1001L).orElse(null);
		
		reforco.setArea("Estatística");
		reforco.setDisciplina(DisciplinaEnum.MATEMATICA);
		reforco.setHoraInicio(LocalDateTime.of(2019, Month.NOVEMBER, 15, 20, 00));
		reforco.setUpdated(LocalDateTime.now());
		reforco.setVagas(50);
		
		this.reforcoService.atualizarReforco(reforco);
	}
	
	/**
	 * ======================================= CRU(DELETE) ===========================================
	 */
	
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/reforcos.sql" })
	public void excluirReforcoMustPass() {
		this.reforcoService.removerReforco(1001);
		Reforco reforco = this.reforcoRepository.findById(1001L).orElse(null);
		Assert.assertNull(reforco);
	}
	/**
	 * ====================================== DESATIVAR ============================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql",
			"/dataset/usuarios.sql",
			"/dataset/professor.sql",
			"/dataset/reforcos.sql" })
	public void desativarReforcoMustPass() {
		Reforco reforco = this.reforcoRepository.findById(1001L).orElse(null);
		
		this.reforcoService.desativarReforco(reforco);
		Assert.assertNotNull(reforco.getId());
	}

	

}
