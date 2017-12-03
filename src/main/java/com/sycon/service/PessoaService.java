package com.sycon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sycon.model.Pessoa;
import com.sycon.model.User;
import com.sycon.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private UserService serviceUser;

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Pessoa findOne(Long id) {
		return repository.findOne(id);
	}

	public Pessoa saveAndFlush(Pessoa pessoa) {
		return repository.saveAndFlush(pessoa);
	}

	public Pessoa save(Pessoa pessoa) {
		User user = pessoa.getUser();
		pessoa.setUser(serviceUser.encryptPass(user));
		return repository.save(pessoa);
	}

	public void delete(Long codigo) {
		Pessoa pessoa = repository.findOne(codigo);
		pessoa.getUser().setRoles(null);
		repository.delete(codigo);
	}

	public Page<Pessoa> porNome(String nome, Pageable pageable) {
		return repository.porNome(nome, pageable);
	}
	
	public List<Pessoa> pesquisa(String nome, String cpfCnpj){
		return repository.findByNomeOrCpfCnpj(nome, cpfCnpj);
	}
	
}
