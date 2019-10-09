package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

public class MatriculaCurso extends Curso implements Serializable {
	
	@NotBlank
	private LocalDateTime dataMatricula;
	
	@NotBlank
	private LocalDateTime dataVencimentoMatricula;
	
	@Transient
	private Integer tempoRestante;

}
