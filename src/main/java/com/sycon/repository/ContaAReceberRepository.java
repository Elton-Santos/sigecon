package com.sycon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sycon.model.Conta;
import com.sycon.model.ContaAPagar;
import com.sycon.model.ContaAReceber;
import com.sycon.model.Pessoa;

public interface ContaAReceberRepository extends JpaRepository<ContaAReceber, Long> {

	List<ContaAReceber> findByNomeCROrBoleto(String nomeCR, String Boleto);
	
	List<ContaAReceber> findByContaCR(Conta conta);
}
