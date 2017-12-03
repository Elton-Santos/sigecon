package com.sycon.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sycon.model.Conta;
import com.sycon.model.ContaAPagar;
import com.sycon.repository.ContaAPagarRepository;

@Service
public class ContaAPagarService {

	@Autowired
	private ContaAPagarRepository repository;

	public List<ContaAPagar> findAll() {
		return repository.findAll();
	}

	public ContaAPagar findOne(Long id) {
		return repository.findOne(id);
	}

	public ContaAPagar saveAndFlush(ContaAPagar contaAPagar) {
		return repository.saveAndFlush(contaAPagar);
	}

	public ContaAPagar save(ContaAPagar contaAPagar) {
		System.out.println(contaAPagar.getDtPagamentoCP());
		return repository.save(contaAPagar);
	}

	public void delete(Long codigo) {
		repository.delete(codigo);
	}

	public List<ContaAPagar> pesquisa(String nomeCP, String notaFiscal) {
		return repository.findByNomeCPOrNotaFiscal(nomeCP, notaFiscal);
	}
	
	public List<ContaAPagar> findByContaCP(Conta conta){
		return repository.findByContaCP(conta);
	}
	
	public List<ContaAPagar> findByContaCPAndDtPagamentoCPBetween(Conta conta, Date data, Date dataEnd){
		return repository.findByContaCPAndDtPagamentoCPBetween(conta, data, dataEnd);
	}
	
	public List<ContaAPagar> findByDtPagamentoCPBetween(Date DtPagamentoCP, Date dtPagamento){
		return repository.findByDtPagamentoCPBetween(DtPagamentoCP, dtPagamento);
	}
	
	

}
