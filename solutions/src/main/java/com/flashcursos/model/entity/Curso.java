package com.flashcursos.model.entity;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
<<<<<<< HEAD
=======
import lombok.EqualsAndHashCode;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

@Data
@Entity
public class Curso extends AbstractAulas implements Serializable{
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;

=======
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
	@NotNull
	private LocalDateTime created;
	
	@PrePersist
	protected void refreshCreated()
	{
		if ( this.getCreated() == null )
		{
			this.setCreated( LocalDateTime.now() );
		}
	}*/
	
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DificuldadeEnum dificuldadeEnum;
	
	@NotBlank
	private Integer cargaHoraria;
	
	@NotNull
	@OneToMany(targetEntity = MatriculaCurso.class, fetch = FetchType.LAZY)	
	private List<MatriculaCurso> matriculas = new ArrayList <MatriculaCurso>();

}
