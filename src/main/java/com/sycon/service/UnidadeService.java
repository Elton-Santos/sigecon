package com.sycon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sycon.model.Condominio;
import com.sycon.model.Unidade;
import com.sycon.repository.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository repository;

	public List<Unidade> findAll() {
		return repository.findAll();
	}
	
	public List<Unidade> pesquisa(Unidade unidade){
		return repository.findByCondominioOrNomeUnidadeOrProprietarioUnidade(unidade.getCondominio(), unidade.getNomeUnidade(), unidade.getProprietarioUnidade());
	}

	public Unidade findOne(Long id) {
		return repository.findOne(id);
	}

	public Unidade saveAndFlush(Unidade unidade) {
		return repository.saveAndFlush(unidade);
	}

	public Unidade save(Unidade unidade) {
		return repository.save(unidade);
	}

	public void delete(Long codigo) {
		Unidade unidade = this.findOne(codigo);
		unidade.setProprietarioUnidade(null);
		unidade.setCondominio(null);
		repository.delete(codigo);
	}

	public Page<Unidade> porNome(String nome, Pageable pageable) {
		return repository.porNome(nome, pageable);
	}
	

}
