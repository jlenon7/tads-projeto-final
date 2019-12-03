package com.flashcursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcursos.model.entity.MatriculaCurso;

public interface MatriculaRepository extends JpaRepository<MatriculaCurso, Long> {

}
