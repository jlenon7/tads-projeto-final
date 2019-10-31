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
import com.flashcursos.model.entity.AbstractAulas;
import com.flashcursos.model.repository.ProfessorRepository;
import com.flashcursos.model.repository.ReforcoRepository;
import com.flashcursos.model.service.ReforcoService;

public class ReforcoTests extends AbstractIntegrationTests {

	@Autowired
	private ReforcoService reforcoService;

	@Autowired
	private ReforcoRepository reforcoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;

	/**
	 * ====================================== LISTAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/reforcos.sql" })
	public void listarReforcosMustPass() {
		List<Reforco> reforcos = this.reforcoService.listarReforcos();
		Assert.assertEquals(reforcos.size(), 2);

	}

	/**
	 * ====================================== CADASTRAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", 
			"/dataset/usuarios.sql", 
			"/dataset/professor.sql", 
			"/dataset/reforcos.sql" })
	
	/**
	 * Professor
	 */
	
	public void cadastrarReforcoMustPass() {
		Reforco reforco = new Reforco();
		
		reforco.setDisciplina(DisciplinaEnum.MATEMATICA);
		reforco.setArea("Funções Matemáticas");
		reforco.setHoraInicio(LocalDateTime.of(2019, Month.DECEMBER, 01, 17, 00));
		reforco.setVagas(10);
		Professor ministrante = this.professorRepository.findById(1001L).orElse(null);
		reforco.setMinistrante(ministrante);
		reforco.setDisponivel(true);
		
		reforcoService.cadastrarReforco(reforco);
		Assert.assertNotNull(reforco.getId());
		
	}

	/**
	 * 
	 * ========================= ATUALIZAR ==============================
	 */

	/**
	 * =========================== DETALHAR =============================
	 */
	
	/**
	 * ========================== EXCLUIR ===============================
	 */

	

}
