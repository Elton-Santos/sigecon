package com.sycon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sycon.model.Condominio;
import com.sycon.model.Pessoa;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {

	@Query("select e from Condominio e where " + "condominio like %?1% or ?1 is null")
	Page<Condominio> porNome(String condominio, Pageable pageable);
	
	List<Condominio> findByCnpjOrNomeCondominio(String cnpj, String nomeCondominio);
}
