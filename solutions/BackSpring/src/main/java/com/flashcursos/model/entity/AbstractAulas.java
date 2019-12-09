package com.flashcursos.model.entity;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAulas extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AbstractAulas(Long id) {
		super.setId(id);
	}
	
	@NotBlank
	private String area;
	
	@NotNull
	@ManyToOne(targetEntity = Professor.class, fetch = FetchType.LAZY, optional = false)
	private Professor ministrante;
	
	@NotNull
	private Boolean disponivel;

}
