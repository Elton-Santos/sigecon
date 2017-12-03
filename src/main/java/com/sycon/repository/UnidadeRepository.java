package com.sycon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sycon.model.Condominio;
import com.sycon.model.Pessoa;
import com.sycon.model.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {


	@Query("select e from Unidade e where " + "unidade like %?1% or ?1 is null")
	Page<Unidade> porNome(String nome, Pageable pageable);
	
	List<Unidade> findByCondominioOrNomeUnidadeOrProprietarioUnidade(Condominio condominio, String nome, Pessoa proprietario);
	
}
