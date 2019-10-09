package com.flashcursos.model.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public abstract class Professor extends Usuario {
	
	/**
	 * ========================== ENTIDADES ==========================
	 */
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private AreaConhecimentoEnum areaConhecimento;
	
	/**
	 * ========================== MÉTODOS ==========================
	 */

}
