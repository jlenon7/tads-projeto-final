package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MatriculaCurso implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private LocalDateTime dataMatricula;
	
	@NotBlank
	private LocalDateTime dataVencimentoMatricula;
	
	@Transient
	private Integer tempoRestante;
	
	@NotNull
	@ManyToOne(targetEntity = Aluno.class, fetch = FetchType.LAZY)	
	private Aluno aluno;
	
	@NotNull
	@ManyToOne(targetEntity = Curso.class, fetch = FetchType.LAZY)	
	private Curso curso;

}
