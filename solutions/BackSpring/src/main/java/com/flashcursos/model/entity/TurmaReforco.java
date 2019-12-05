package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
@Entity
public class TurmaReforco extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(targetEntity = TurmaReforcoAluno.class, mappedBy = "turma", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<TurmaReforcoAluno> alunosTurma = new ArrayList<TurmaReforcoAluno>();
	
	@NotNull
	@ManyToOne(targetEntity = Reforco.class, fetch = FetchType.LAZY)	
	private Reforco reforco;
	
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
	private Boolean disponivel;

}
