package com.sycon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sycon.configuration.PageWrapper;
import com.sycon.model.Pessoa;
import com.sycon.repository.PessoaRepository;
import com.sycon.service.PessoaService;

@RestController
@RequestMapping("/condominio")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@Autowired
	private PessoaRepository repository;

	private static final String INDEX = "admin/consultas/consultarPessoa";
	private static final String CADASTRO = "admin/cadastros/cadastrarPessoa";

	@RequestMapping("/pessoa")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("Pessoa", new Pessoa());
		view.addObject("listaPessoas", service.findAll());
		return view;
	}

	@RequestMapping(value = "/pessoa", params = { "nome", "cpfCnpj" })
	public ModelAndView pesquisar(@RequestParam(value = "nome") String nome,
			@RequestParam(value = "cpfCnpj") String cpfCnpj) {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("Pessoa", new Pessoa());
		// Se os valores dos campos forem brancos, lista todos os registros
		if (nome.isEmpty() && cpfCnpj.isEmpty())
			return findAll();
		else
			view.addObject("listaPessoas", service.pesquisa(nome, cpfCnpj));
		return view;
	}

	@RequestMapping("pessoa/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Pessoa pessoa) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject(pessoa);
		return view;
	}

	@RequestMapping("pessoa/novo")
	public ModelAndView save(Pessoa pessoa) {
		return new ModelAndView(CADASTRO);
	}

	@RequestMapping(value = "pessoa/novo", method = RequestMethod.POST)
	public ModelAndView save(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return save(pessoa);
		}

		attributes.addFlashAttribute("mensagem", "Entidade salva com sucesso.");
		service.save(pessoa);
		return new ModelAndView("redirect:/condominio/pessoa");
	}

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		service.delete(id);
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("Pessoa", new Pessoa());
		view.addObject("listaPessoas", service.findAll());
		return view;
	}
}
