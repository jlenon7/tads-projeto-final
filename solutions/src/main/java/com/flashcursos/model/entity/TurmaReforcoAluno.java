package com.flashcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (callSuper = true)
public class TurmaReforcoAluno extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1382783987670943643L;

	@ManyToOne (targetEntity = TurmaReforco.class, optional = false, fetch = FetchType.LAZY)
	private TurmaReforco turma;
	
	@ManyToOne (targetEntity = Aluno.class, optional = false, fetch = FetchType.LAZY)
	private Aluno aluno;
}
