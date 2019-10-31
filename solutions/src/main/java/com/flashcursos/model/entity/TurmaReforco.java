package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.FetchType;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
<<<<<<< HEAD
=======
import lombok.EqualsAndHashCode;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

@Data
@Entity
public class TurmaReforco extends AbstractEntity implements Serializable {
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;

=======
	//private static final long serialVersionUID = 1L;
	
	/*@Column(unique = true, nullable = false)
	@NotBlank
	private Integer id; */
	
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	@NotNull
	@OneToMany(targetEntity = Aluno.class, fetch = FetchType.LAZY)	
	private List<Aluno> alunos = new ArrayList <Aluno>();
	
	@NotBlank
	private LocalDateTime dataInicio;
	
	@NotBlank
	private LocalDateTime dataFim;
	
	@NotBlank
	private Boolean disponivel;
<<<<<<< HEAD
=======
	
	/*public TurmaReforco(Long id) {
		super.setId(id);
	}*/
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

}
