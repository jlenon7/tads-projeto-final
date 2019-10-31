package com.flashcursos.model.entity;

import java.io.Serializable;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
=======
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
=======
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractAulas extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String area;
	
	@NotBlank
	private String ministrante;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	
	@NotNull
	private Boolean disponivel;

}
