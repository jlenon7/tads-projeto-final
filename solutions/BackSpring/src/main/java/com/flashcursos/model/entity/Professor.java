package com.flashcursos.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario{
	private static final long serialVersionUID = 1L;
	
	public Professor(Long id) {
		super.setId(id);
	}
	
	@Enumerated( EnumType.ORDINAL )
	private AreaConhecimentoEnum areaConhecimento;
	
	@JsonIgnoreProperties("ministrante")
	@OneToMany(
			targetEntity = Curso.class,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			fetch = FetchType.LAZY,
			mappedBy = "ministrante",
			orphanRemoval = true
			)
	private List<Curso> aulas = new ArrayList<Curso>();

}
