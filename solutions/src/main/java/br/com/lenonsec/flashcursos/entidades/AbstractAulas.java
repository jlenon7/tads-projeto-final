package br.com.lenonsec.flashcursos.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractAulas extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Entidades😎👌😎👌😎👌😎👌😎👌😎👌😎
	 */
	
	@NotBlank
	private String area;
	
	@NotBlank
	private String ministrante;
	
	@NotNull
	private Boolean disponivel;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Métodos😎👌😎👌😎👌😎👌😎👌😎👌😎👌
	 */

}
