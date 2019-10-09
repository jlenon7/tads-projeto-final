package com.flashcursos.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class AbstractAulas extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ========================== ENTIDADES ==========================
	 */
	
	@NotBlank
	private String area;
	
	@NotBlank
	private String ministrante;
	
	@NotNull
	private Boolean disponivel;
	
	/**
	 * ========================== MÉTODOS ==========================
	 */

}
