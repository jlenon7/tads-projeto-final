package br.com.lenonsec.flashcursos.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

public class MatriculaCurso extends Curso implements Serializable{
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Entidades😎👌😎👌😎👌😎👌😎👌😎👌😎
	 */
	
	@NotBlank
	private LocalDateTime dataMatricula;
	
	@NotBlank
	private LocalDateTime dataVencimentoMatricula;
	
	@Transient
	private Integer tempoRestante;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Métodos😎👌😎👌😎👌😎👌😎👌😎👌😎👌
	 */
}
