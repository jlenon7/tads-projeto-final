package br.com.lenonsec.flashcursos.entidades;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Reforco extends AbstractAulas{
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Entidades😎👌😎👌😎👌😎👌😎👌😎👌😎
	 */
	
	@NotBlank
	private Integer vagas;
	
	@NotBlank
	private LocalDateTime horaInicio;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DisciplinaEnum disciplina;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Métodos😎👌😎👌😎👌😎👌😎👌😎👌😎👌
	 */

}
