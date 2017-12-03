package com.sycon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sycon.model.Conta;
import com.sycon.model.ContaAReceber;
import com.sycon.model.Pessoa;
import com.sycon.repository.ContaAReceberRepository;

@Service
public class ContaAReceberService {

	@Autowired
	private ContaAReceberRepository repository;

	public List<ContaAReceber> findAll() {
		return repository.findAll();
	}

	public ContaAReceber findOne(Long id) {
		return repository.findOne(id);
	}

	public ContaAReceber saveAndFlush(ContaAReceber contaAReceber) {
		return repository.saveAndFlush(contaAReceber);
	}

	public ContaAReceber save(ContaAReceber contaAReceber) {
		return repository.save(contaAReceber);
	}

	public void delete(Long codigo) {
		repository.delete(codigo);
	}

	public List<ContaAReceber> pesquisa(String nomeCR, String boleto) {
		return repository.findByNomeCROrBoleto(nomeCR, boleto);
	}
	
	public List<ContaAReceber> findByContaCR(Conta conta){
		return repository.findByContaCR(conta);
	}

}
