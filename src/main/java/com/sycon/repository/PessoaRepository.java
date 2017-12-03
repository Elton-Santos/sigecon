package com.sycon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sycon.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select e from Pessoa e where " + "pessoa like %?1% or ?1 is null")
	Page<Pessoa> porNome(String pessoa, Pageable pageable);
	
	List<Pessoa> findByNomeOrCpfCnpj(String nome, String cpfCnpj);
}
