package com.flashcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Curso extends AbstractAulas implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DificuldadeEnum dificuldade;
	
	@NotNull
	private Integer cargaHoraria;
	
	//@NotNull
	//@OneToMany(targetEntity = MatriculaCurso.class, fetch = FetchType.LAZY)	
	//private List<MatriculaCurso> matriculas = new ArrayList <MatriculaCurso>();

}
