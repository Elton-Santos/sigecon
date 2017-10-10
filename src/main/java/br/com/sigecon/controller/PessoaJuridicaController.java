package br.com.sigecon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sigecon.models.entidades.PessoaJuridica;
import br.com.sigecon.repositoty.PessoaJuridicaRepository;
import br.com.sigecon.service.PessoaJuridicaService;

@Controller
@RequestMapping("juridica")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService service;

	@Autowired
	PessoaJuridicaRepository repository;

	private static final String INDEX = "/entidades/listaPessoaJuridica";

	@RequestMapping("/pesquisaPessoaJuridica")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("pessoaJuridica", new PessoaJuridica());
		view.addObject("listaPessoaJuridica", service.findAll());
		return view;
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(PessoaJuridica pessoaJuridica) {
		ModelAndView view = new ModelAndView("entidades/cadastroPessoaJuridica");
		return view;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid PessoaJuridica pessoaJuridica, BindingResult result,
			RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView("redirect:/entidades/add");
		if (result.hasErrors()) {
			return add(pessoaJuridica);
		}
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		service.save(pessoaJuridica);
		return view;
	}

	@RequestMapping
	public ModelAndView pesquisar(PessoaJuridica pessoaJuridica) {
		ModelAndView view = new ModelAndView(INDEX);
		if (pessoaJuridica.getRazaoSocial() == "" && pessoaJuridica.getCnpj() == "") {
			view.addObject("listaPessoaJuridica", service.findAll());
		} else if (pessoaJuridica.getRazaoSocial() == "") {
			view.addObject("listaPessoaJuridica", repository.findByCnpj((pessoaJuridica.getCnpj())));
		} else if (pessoaJuridica.getCnpj() == "") {
			view.addObject("listaPessoaJuridica", repository.porNome(pessoaJuridica.getRazaoSocial()));
		} else {
			view.addObject("listaPessoaJuridica", service.findAll());
		}
		return view;
	}

}
