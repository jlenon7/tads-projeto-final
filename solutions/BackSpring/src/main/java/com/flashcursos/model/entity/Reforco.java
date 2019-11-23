package com.flashcursos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Reforco extends AbstractAulas{
	private static final long serialVersionUID = 1L;

	//@NotBlank
	private Integer vagas;
	
	//@NotBlank
	private LocalDateTime horaInicio;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DisciplinaEnum disciplina;
	
}
