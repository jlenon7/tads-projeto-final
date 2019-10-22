package br.com.lenonsec.flashcursos.entidades;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class Aluno extends Usuario{
	
	@ManyToOne(targetEntity = MatriculaCurso.class, fetch = FetchType.LAZY, optional = false)
	private MatriculaCurso matriculacurso;
	
	@ManyToOne(targetEntity = TurmaReforco.class, fetch = FetchType.LAZY, optional = false)	
	private TurmaReforco turmareforco;

}
