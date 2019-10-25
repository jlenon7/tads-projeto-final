package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class TurmaReforco extends AbstractEntity implements Serializable {
	//private static final long serialVersionUID = 1L;
	
	/*@Column(unique = true, nullable = false)
	@NotBlank
	private Integer id; */
	
	@NotNull
	@OneToMany(targetEntity = Aluno.class, fetch = FetchType.LAZY)	
	private List<Aluno> alunos = new ArrayList <Aluno>();
	
	@NotBlank
	private LocalDateTime dataInicio;
	
	@NotBlank
	private LocalDateTime dataFim;
	
	@NotBlank
	private Boolean disponivel;
	
	/*public TurmaReforco(Long id) {
		super.setId(id);
	}*/

}
