package com.flashcursos.model.entity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

	@Id
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
	}
	
}
