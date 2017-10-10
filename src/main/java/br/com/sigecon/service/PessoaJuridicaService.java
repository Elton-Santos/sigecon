package br.com.sigecon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigecon.models.entidades.PessoaJuridica;
import br.com.sigecon.repositoty.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository repository;

	public List<PessoaJuridica> findAll() {
		return repository.findAll();
	}

	public PessoaJuridica findOne(Long id) {
		return repository.findOne(id);
	}

	public PessoaJuridica saveAndFlush(PessoaJuridica pessoaJuridica) {
		return repository.saveAndFlush(pessoaJuridica);
	}

	public PessoaJuridica save(PessoaJuridica pessoaJuridica) {
		return repository.save(pessoaJuridica);
	}

	public void delete(Long codigo) {
		repository.delete(codigo);
	}
}
