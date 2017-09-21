package br.com.sigecon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sigecon.models.entidades.PessoaFisica;
import br.com.sigecon.repositoty.PessoaFisicaRepository;
import br.com.sigecon.service.PessoaFisicaService;

@Controller
@RequestMapping("/entidades")
public class PessoaController {

	@Autowired
	private PessoaFisicaService service;

	@Autowired
	private PessoaFisicaRepository repository;

	private static final String INDEX = "/entidades/listaPessoaFisica";

	@RequestMapping("/")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("PessoaFisica", new PessoaFisica());
		view.addObject("listaPessoas", service.findAll());
		return view;
	}

	@RequestMapping("/add")
	public ModelAndView add(PessoaFisica pessoaFisica) {
		ModelAndView view = new ModelAndView("/entidades/cadastroPessoa");
		view.addObject("listaPessoas", pessoaFisica);

		return view;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return add(service.findOne(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid PessoaFisica pessoaFisica, BindingResult result, RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView("redirect:/entidades/add");
		if (result.hasErrors()) {
			return add(pessoaFisica);
		}
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		service.save(pessoaFisica);
		return view;

	}

	@RequestMapping
	public ModelAndView pesquisar(PessoaFisica pessoaFisica) {
		ModelAndView view = new ModelAndView(INDEX);
		if (pessoaFisica.getNome() == "" && pessoaFisica.getCpf() == "") {
			view.addObject("listaPessoas", service.findAll());
		} else if (pessoaFisica.getNome() == "") {
			view.addObject("listaPessoas", repository.findByCpf((pessoaFisica.getCpf())));
		} else if (pessoaFisica.getCpf() == "") {
			view.addObject("listaPessoas", repository.porNome(pessoaFisica.getNome()));
		} else {
			view.addObject("listaPessoas", service.findAll());
		}
		return view;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/entidades";
	}
}
