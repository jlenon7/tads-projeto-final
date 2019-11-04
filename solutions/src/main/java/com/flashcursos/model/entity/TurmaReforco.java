package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class TurmaReforco extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/* OrphanRemoval quer dizer que se excluirmos o departamento, se o funcionário
	 *  não estiver vinculado a outro departamento ele será excluído
	 * Enquanto que, cascade remove quer dizer que se eu remover o departamento os funcionários serão removidos*/
	@JsonIgnoreProperties("turmareforco")
	@OneToMany(targetEntity = Aluno.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			fetch = FetchType.EAGER, mappedBy = "turmareforco", orphanRemoval = true)	
	private List<Aluno> alunos = new ArrayList <Aluno>();
	
	@NotBlank
	private LocalDateTime dataInicio;
	
	@NotBlank
	private LocalDateTime dataFim;
	
	@NotBlank
	private Boolean disponivel;

}
