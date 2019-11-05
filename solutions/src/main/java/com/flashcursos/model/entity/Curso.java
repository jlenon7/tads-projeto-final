package com.flashcursos.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Curso extends AbstractAulas implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DificuldadeEnum dificuldade;
	
	// @NotBlank
	private Integer cargaHoraria;
	
	@NotNull
	@OneToMany(targetEntity = MatriculaCurso.class, fetch = FetchType.LAZY)	
	private List<MatriculaCurso> matriculas = new ArrayList <MatriculaCurso>();

}
