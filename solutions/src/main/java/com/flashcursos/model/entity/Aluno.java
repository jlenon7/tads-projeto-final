package com.flashcursos.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	public Aluno(Long id) {
		super.setId(id);
	}
	
	@ManyToOne(targetEntity = TurmaReforco.class, fetch = FetchType.LAZY, optional = false)	
	private TurmaReforco turmareforco;
=======
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(targetEntity = TurmaReforco.class, fetch = FetchType.LAZY, optional = false)	
	private TurmaReforco turmareforco;
	
	public Aluno(Long id) {
		super.setId(id);
	}
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

}
