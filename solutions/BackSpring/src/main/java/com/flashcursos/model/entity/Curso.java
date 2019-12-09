package com.flashcursos.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@JsonIgnoreProperties({"curso", "aluno"})
	@OneToMany(
			targetEntity = MatriculaCurso.class,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			fetch = FetchType.LAZY,
			mappedBy = "curso",
			orphanRemoval = true
			)
	private List<MatriculaCurso> matriculas = new ArrayList<MatriculaCurso>();

}
