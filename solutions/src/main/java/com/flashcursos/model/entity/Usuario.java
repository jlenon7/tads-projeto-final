package com.flashcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

import org.springframework.util.Assert;

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
public class Usuario extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ========================== ENTIDADES ==========================
	 */	
	
	@NotBlank
	@Column(unique = true, nullable = false, length = 40)
	private String email;
	
	@NotBlank
	private String senha;
	
	private Boolean disponivel;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private TipoUsuarioEnum tipousuario;
	
	/**
	 * ========================== MÉTODOS ==========================
	 */

}

