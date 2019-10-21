package br.com.lenonsec.flashcursos.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class TurmaReforco extends Aluno implements Serializable{

	@Column(unique = true, nullable = false)
	@NotBlank
	private Integer id;
	
	@NotBlank
	private List<Aluno> alunos;
	
	@NotBlank
	private LocalDateTime dataInicio;
	
	@NotBlank
	private LocalDateTime dataFim;
	
	@NotBlank
	private Boolean disponivel;
	
	public TurmaReforco(Long id) {
		super.setId(id);
	}

}
