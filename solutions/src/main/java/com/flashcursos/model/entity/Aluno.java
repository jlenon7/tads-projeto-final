package com.flashcursos.model.entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.flashcursos.model.entity.MatriculaCurso;
import com.flashcursos.model.entity.TurmaReforco;

public abstract class Aluno extends Usuario {
	
	@ManyToOne(targetEntity = MatriculaCurso.class,
			fetch = FetchType.LAZY,
			optional = false)
	private MatriculaCurso matriculacurso;
	
	@ManyToOne(targetEntity = TurmaReforco.class,
			fetch = FetchType.LAZY,
			optional = false)
	private TurmaReforco turmareforco;

}
