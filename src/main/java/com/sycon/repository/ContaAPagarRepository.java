package com.sycon.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import com.sycon.model.Conta;
import com.sycon.model.ContaAPagar;

public interface ContaAPagarRepository extends JpaRepository<ContaAPagar, Long> {

	// @Query("select e from ContaAPagar e where " + "ContaAPagar like %?1% or ?1 is
	// null")
	// Page<ContaAPagar> porNome(String contaAPagar, Pageable pageable);

	List<ContaAPagar> findByNomeCPOrNotaFiscal(String nomeCP, String notaFiscal);

	List<ContaAPagar> findByContaCPAndDtPagamentoCPBetween(Conta conta,@DateTimeFormat(pattern = "dd/MM/yyyy") Date dateStart, @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateEnd);

	List<ContaAPagar> findByContaCP(Conta conta);
	
	List<ContaAPagar> findByDtPagamentoCPBetween(Date DtPagamentoCP, Date dtDate);
}
