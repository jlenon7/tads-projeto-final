package com.flashcursos.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flashcursos.model.entity.Aluno;
import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.entity.TurmaReforcoAluno;
import com.flashcursos.model.repository.MatriculaRepository;

@Service
@Transactional
public class MatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;

	/**
	 * Serviço para inserir uma nova matricula
	 * 
	 * @param matricula
	 * @return
	 */

	public MatriculaCurso cadastrarMatricula(MatriculaCurso matricula) {

		LocalDateTime dataMatricula = (LocalDateTime.now());
		matricula.setDataMatricula(dataMatricula);
		LocalDateTime daquiUmAno = dataMatricula.plus(1, ChronoUnit.YEARS);

		matricula.setDataVencimentoMatricula(daquiUmAno);

		matricula.setDisponivel(true);
		return this.matriculaRepository.save(matricula);
	}

	/**
	 * Serviço para atualizar o cadastro de uma matricula
	 * 
	 * @param matricula
	 * @return
	 */
	public MatriculaCurso atualizarMatricula(MatriculaCurso matricula) {
		return this.matriculaRepository.save(matricula);
	}

	/**
	 * Serviço para listar as matriculas cadastradas
	 * 
	 * @return
	 */
	@Scheduled(cron = "47 18 * * * *")
	public void updateSituacaoMatricula() {
		System.out.println("rodo");
		List<MatriculaCurso> matriculas = this.matriculaRepository.findAll();
		for (MatriculaCurso matricula : matriculas) {
			boolean isAfter = matricula.getDataVencimentoMatricula().isAfter(LocalDateTime.now());
			if (isAfter == false) {
				matricula.setDisponivel(false);
			} else {
				matricula.setDisponivel(true);
			}
		}
	}

	public List<MatriculaCurso> listarMatricula() {

		List<MatriculaCurso> matriculas = this.matriculaRepository.findAll();
		for (MatriculaCurso matricula : matriculas) {
			boolean isAfter = matricula.getDataVencimentoMatricula().isAfter(LocalDateTime.now());
			if (isAfter == false) {
				matricula.setDisponivel(false);
			} else {
				matricula.setDisponivel(true);
			}
		}

		return this.matriculaRepository.findAll();
	}

	/**
	 * Serviço para detalhar o cadastro de uma matricula
	 * 
	 * @param id
	 * @return
	 */
	public MatriculaCurso detalharMatricula(long id) {
		MatriculaCurso matricula = this.matriculaRepository.findById(id).orElse(null);
		Assert.notNull(matricula, "O ID " + id + " não foi encontrado.");
		return matricula;
	}

	/**
	 * Serviço que remove uma matricula cadastrada
	 * 
	 * @param id
	 */
	public void removerMatricula(long id) {
		this.matriculaRepository.deleteById(id);
	}

	/**
	 * Serviço que desativa uma matricula cadastrado
	 * 
	 * @param id
	 * @return
	 */
	public MatriculaCurso desativarMatricula(MatriculaCurso matricula) {
		matricula.setDisponivel(false);
		return this.matriculaRepository.save(matricula);
	}
}