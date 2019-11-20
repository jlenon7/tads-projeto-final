package com.flashcursos.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
}
