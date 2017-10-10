package br.com.sigecon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigecon.models.entidades.PessoaFisica;
import br.com.sigecon.repositoty.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository repository;

	public List<PessoaFisica> findAll() {
		return repository.findAll();
	}

	public PessoaFisica findOne(Long id) {
		return repository.findOne(id);
	}

	public PessoaFisica saveAndFlush(PessoaFisica pessoaFisica) {
		return repository.saveAndFlush(pessoaFisica);
	}

	public PessoaFisica save(PessoaFisica pessoaFisica) {
		return repository.save(pessoaFisica);
	}

	public void delete(Long codigo) {
		repository.delete(codigo);
	}

}
