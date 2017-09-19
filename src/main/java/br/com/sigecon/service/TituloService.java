package br.com.sigecon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigecon.models.financeiro.Titulo;
import br.com.sigecon.repositoty.TituloRepository;

@Service
public class TituloService {

	@Autowired
	private TituloRepository repository;

	public Titulo save(Titulo titulo) {
		return repository.save(titulo);
	}

}
