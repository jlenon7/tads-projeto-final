package com.flashcursos.model.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Curso {
	
	/**
	 * ========================== ENTIDADES ==========================
	 */
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DificuldadeEnum dificuldadeEnum;
	
	@NotBlank
	private Integer cargaHoraria;
}
