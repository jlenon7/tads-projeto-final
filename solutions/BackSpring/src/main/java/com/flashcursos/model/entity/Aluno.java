package com.flashcursos.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	@OneToMany(targetEntity = TurmaReforcoAluno.class, mappedBy = "aluno", fetch = FetchType.LAZY)
	private List<TurmaReforco> turmasReforco = new ArrayList<TurmaReforco>();
}
