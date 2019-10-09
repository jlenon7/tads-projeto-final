package com.flashcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Usuario extends AbstractEntity implements UserDetails {
	private static final long serialVersionUID = -6624327477248695198L;
	
	/**
	 * ========================== ENTIDADES ==========================
	 */	
	
	@NotBlank
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	private String nome;
	
	@Column(unique = true, nullable = false, length = 15)
	@NotBlank
	private String cpf;
	
	@NotBlank
	@Column(nullable = false, length = 100, unique = true)
	@Size(max = 100)
	private String email;
	
	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	private String celular;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	private String senha;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private TipoUsuarioEnum tipoUsuario;
	
	@NotBlank
	private LocalDate nascimento;
	
	/**
	 * ========================== MÉTODOS ==========================
	 */

}

