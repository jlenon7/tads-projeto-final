package com.flashcursos.model.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flashcursos.model.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	/*
	 * VERIFICAR !!!!!!
	 * @Override
	@EntityGraph(attributePaths = "departamento")
	public Optional<Professor> findById(Long id);
	
	
	public Page<Professor> findByDepartamentoId(Long id, Pageable pageable);
	
	@Query("FROM Professor professor "
			+ "WHERE "
			+ "(professor.nome LIKE '%' || :nome || '%' OR :nome IS NULL) "
			+ " AND (professor.cpf LIKE '%' || :cpf || '%' OR :cpf IS NULL) "
			+ "AND (( professor.dataNascimento >= :dataNascimentoInicial OR CAST(:dataNascimentoInicial AS timestamp) IS NULL) "
			+ "AND ( professor.dataNascimento <= :dataNascimentoFinal OR CAST(:dataNascimentoFinal AS timestamp) IS NULL))")
	public Page<Professor> findByFilters(@Param("nome") String nome, @Param("cpf") String cpf, 
			@Param("dataNascimentoInicial") LocalDate dataNascimentoInicial, @Param("dataNascimentoFinal") LocalDate dataNascimentoFinal, Pageable pageable);
	
	
	@Query("FROM Professor professor "
			+ "WHERE ( professor.nome LIKE '%' || :nome || '%' OR :nome IS NULL) AND "
			+ "( professor.cpf LIKE '%' || :cpf || '%' OR :cpf IS NULL)")
	public Page<Professor> findByFilters(@Param("nome") String nome, @Param("cpf") String cpf, Pageable pageable);
}*/

}

