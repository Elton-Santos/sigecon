package com.sycon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sycon.model.Condominio;
import com.sycon.model.Pessoa;
import com.sycon.model.Unidade;
import com.sycon.repository.CondominioRepository;

@Service
public class CondominioService {

	@Autowired
	private CondominioRepository repository;
	
	@Autowired
	private UnidadeService serviceUnidade;

	public List<Condominio> findAll() {
		return repository.findAll();
	}

	public Condominio findOne(Long id) {
		return repository.findOne(id);
	}

	public Condominio saveAndFlush(Condominio condominio) {
		return repository.saveAndFlush(condominio);
	}

	public Condominio save(Condominio condominio) {
		return repository.save(condominio);
	}

	public void delete(Long codigo) {
		Condominio condominio = this.findOne(codigo);
		List<Unidade> unidades = condominio.getUnidades();
		for(int i=0; i<condominio.getUnidades().size(); i++) {
			serviceUnidade.delete(unidades.get(i).getIdUnidade());
		}		
		repository.delete(codigo);
	}

	public Page<Condominio> porNome(String nome, Pageable pageable) {
		return repository.porNome(nome, pageable);
	}
	
	public List<Condominio> pesquisa(String cnpj, String nomeCondominio){
		return repository.findByCnpjOrNomeCondominio(cnpj, nomeCondominio);
	}

}
