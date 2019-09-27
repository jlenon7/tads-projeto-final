package com.flashcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Departamento extends AbstractEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 665426621504404261L;

	@NotBlank
	private String nome;
	
	private String descricao;
}
