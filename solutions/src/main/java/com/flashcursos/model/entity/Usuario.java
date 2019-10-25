package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor // conferir
//@AllArgsConstructor // conferir
@Entity
public class Usuario extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(unique = true, nullable = false, length = 15)
	@NotBlank
	private String cpf;
	
	@NotBlank
	private LocalDate nascimento;
	
	@NotBlank
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Column(nullable = false, length = 100)
	private String senha;
	
	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	private String celular;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private TipoUsuarioEnum tipousuario;
	
	@NotNull
	@Column(nullable = false)
	private Boolean disponivel;
	
	public Usuario(Long id) {
		super.setId(id);
	}

}

