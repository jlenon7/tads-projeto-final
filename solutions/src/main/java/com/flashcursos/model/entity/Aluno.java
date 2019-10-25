package com.flashcursos.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Aluno extends Usuario{
	
	@ManyToOne(targetEntity = TurmaReforco.class, fetch = FetchType.LAZY, optional = false)	
	private TurmaReforco turmareforco;
	
	public Aluno(Long id) {
		super.setId(id);
	}

}
