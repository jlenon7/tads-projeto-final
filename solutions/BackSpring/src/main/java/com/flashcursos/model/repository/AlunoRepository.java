package com.flashcursos.model.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flashcursos.model.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	/*
	 * VERIFICAR !!!!!!!!!!!
	 * @Override
	@EntityGraph(attributePaths = "departamento")
	public Optional<Aluno> findById(Long id);
	
	
	public Page<Aluno> findByDepartamentoId(Long id, Pageable pageable);
	
	@Query("FROM Aluno aluno "
			+ "WHERE "
			+ "(aluno.nome LIKE '%' || :nome || '%' OR :nome IS NULL) "
			+ " AND (aluno.cpf LIKE '%' || :cpf || '%' OR :cpf IS NULL) "
			+ "AND (( aluno.dataNascimento >= :dataNascimentoInicial OR CAST(:dataNascimentoInicial AS timestamp) IS NULL) "
			+ "AND ( aluno.dataNascimento <= :dataNascimentoFinal OR CAST(:dataNascimentoFinal AS timestamp) IS NULL))")
	public Page<Aluno> findByFilters(@Param("nome") String nome, @Param("cpf") String cpf, 
			@Param("dataNascimentoInicial") LocalDate dataNascimentoInicial, @Param("dataNascimentoFinal") LocalDate dataNascimentoFinal, Pageable pageable);
	
	
	@Query("FROM Aluno aluno "
			+ "WHERE ( aluno.nome LIKE '%' || :nome || '%' OR :nome IS NULL) AND "
			+ "( aluno.cpf LIKE '%' || :cpf || '%' OR :cpf IS NULL)")
	public Page<Aluno> findByFilters(@Param("nome") String nome, @Param("cpf") String cpf, Pageable pageable);
}*/ 

}

