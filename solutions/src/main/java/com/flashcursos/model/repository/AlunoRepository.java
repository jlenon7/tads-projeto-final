package com.flashcursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcursos.model.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}

