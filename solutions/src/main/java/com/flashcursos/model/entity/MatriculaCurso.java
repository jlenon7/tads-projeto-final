package com.flashcursos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
=======
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MatriculaCurso implements Serializable{
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;

=======
	
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private LocalDateTime dataMatricula;
	
	@NotBlank
	private LocalDateTime dataVencimentoMatricula;
	
	@Transient
	private Integer tempoRestante;
	
	@NotNull
	@ManyToOne(targetEntity = Aluno.class, fetch = FetchType.LAZY)	
	private Aluno aluno;
	
	@NotNull
	@ManyToOne(targetEntity = Curso.class, fetch = FetchType.LAZY)	
	private Curso curso;

}
