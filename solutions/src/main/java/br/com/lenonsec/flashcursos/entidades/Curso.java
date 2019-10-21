package br.com.lenonsec.flashcursos.entidades;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Curso extends AbstractAulas{
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Entidades😎👌😎👌😎👌😎👌😎👌😎👌😎
	 */
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DificuldadeEnum dificuldadeEnum;
	
	@NotBlank
	private Integer cargaHoraria;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Métodos😎👌😎👌😎👌😎👌😎👌😎👌😎👌
	 */

}
