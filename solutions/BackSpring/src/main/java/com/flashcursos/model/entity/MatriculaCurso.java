package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MatriculaCurso implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime dataMatricula;
	
	@NotNull
	private LocalDateTime dataVencimentoMatricula;
	
	@NotNull
	@ManyToOne(targetEntity = Aluno.class, fetch = FetchType.LAZY)	
	private Aluno aluno;
	
	@NotNull
	@ManyToOne(targetEntity = Curso.class, fetch = FetchType.LAZY)	
	private Curso curso;
	
	@NotNull
	private Boolean disponivel;

}
