package com.flashcursos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Reforco extends AbstractAulas{
	
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
	private LocalDateTime horaInicio;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private DisciplinaEnum disciplina;
	
}
