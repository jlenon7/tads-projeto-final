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

	/* OrphanRemoval quer dizer que se excluirmos o departamento, se o funcionário
	 *  não estiver vinculado a outro departamento ele será excluído
	 * Enquanto que, cascade remove quer dizer que se eu remover o departamento os funcionários serão removidos*/
	
	@OneToMany(targetEntity = TurmaReforcoAluno.class, mappedBy = "turma", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<TurmaReforcoAluno> alunosTurma = new ArrayList<TurmaReforcoAluno>();
	
	@NotNull
	@ManyToOne(targetEntity = Reforco.class, fetch = FetchType.LAZY)	
	private Reforco reforco;
	
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
	private Boolean disponivel;

}
