package com.flashcursos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Reforco {
	
	/**
	 * ========================== ENTIDADES ==========================
	 */
	
	@NotBlank
	private Integer vagas;
	
	@NotBlank
	private LocalDateTime horaInicio;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DisciplinaEnum disciplina;

}
