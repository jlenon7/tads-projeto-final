package com.flashcursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcursos.model.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}

