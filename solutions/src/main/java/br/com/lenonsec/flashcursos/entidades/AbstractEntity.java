package br.com.lenonsec.flashcursos.entidades;

import java.time.LocalDateTime;

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
	
	protected LocalDateTime updated;
	
	@PrePersist
	protected void refreshCreated()
	{
		if ( this.getCreated() == null )
		{
			this.setCreated( LocalDateTime.now() );
		}
	}
	
	@PreUpdate
	protected void refreshUpdated()
	{
		this.refreshCreated();
		this.setUpdated( LocalDateTime.now() );
	}
	
	
}

