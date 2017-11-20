package com.sycon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ModelAndView save(@ModelAttribute @Valid Pessoa pessoa, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return save(pessoa);
		}

		service.save(pessoa);
		attributes.addFlashAttribute("mensagem", "Entidade salva com sucesso.");
		return new ModelAndView("redirect:/condominio/pessoa");
	}

	@RequestMapping
	public ModelAndView pesquisar(Pessoa pessoa, @PageableDefault(size = 6) Pageable pageable,
			HttpServletRequest httpServletRequest) {

		ModelAndView view = new ModelAndView(INDEX);
		PageWrapper<Pessoa> paginaWrapper = new PageWrapper<>(repository.porNome(pessoa.getNome(), pageable),
				httpServletRequest);

		view.addObject("pagina", paginaWrapper);
		return view;
	}

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/condominio/pessoa";
	}
}
