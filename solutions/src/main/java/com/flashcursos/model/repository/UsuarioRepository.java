package com.flashcursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcursos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
