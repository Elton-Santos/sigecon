package com.sycon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sycon.model.Condominio;
import com.sycon.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta> findByDescricaoContaOrCondominioConta(String descricaoConta, Condominio condominio);
	

}
