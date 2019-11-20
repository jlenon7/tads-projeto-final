package com.flashcursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcursos.model.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
