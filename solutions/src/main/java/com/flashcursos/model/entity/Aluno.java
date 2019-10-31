package com.flashcursos.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	public Aluno(Long id) {
		super.setId(id);
	}
	
	@ManyToOne(targetEntity = TurmaReforco.class, fetch = FetchType.LAZY, optional = false)	
	private TurmaReforco turmareforco;

}
