package com.flashcursos.model.entity;

<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario{
	private static final long serialVersionUID = 1L;
	
	public Professor(Long id) {
		super.setId(id);
	}
	
	/*@JsonIgnoreProperties("reforco")
	@OneToMany(targetEntity = Reforco.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			fetch = FetchType.EAGER, mappedBy = "reforco", orphanRemoval = true)
	private List<Reforco> meusReforcos = new ArrayList<Reforco>();
	
	  @JsonIgnoreProperties("curso")
	@OneToMany(targetEntity = Curso.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			fetch = FetchType.EAGER, mappedBy = "curso", orphanRemoval = true)
	private List<Curso> meusCursos = new ArrayList<Curso>();*/

	@Enumerated( EnumType.ORDINAL )
	private AreaConhecimentoEnum areaConhecimento;
=======
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

>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
}
