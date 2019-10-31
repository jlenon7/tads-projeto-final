package com.flashcursos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
<<<<<<< HEAD
=======
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Reforco extends AbstractAulas{
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;

	//@NotBlank
	private Integer vagas;
	
	//@NotBlank
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
	} */
	
	@NotBlank
	private Integer vagas;
	
	@NotBlank
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	private LocalDateTime horaInicio;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DisciplinaEnum disciplina;
	
}
