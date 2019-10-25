package com.flashcursos.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Professor extends Usuario{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private AreaConhecimentoEnum areaConhecimento;
	
	public Professor(Long id) {
		super.setId(id);
	}

}
