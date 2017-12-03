package com.sycon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sycon.model.ContaAReceber;
import com.sycon.repository.ContaAReceberRepository;
import com.sycon.service.ContaAReceberService;
import com.sycon.service.ContaService;
import com.sycon.service.UnidadeService;

@RestController
@RequestMapping("/condominio")
public class ContaAReceberController {

	@Autowired
	private ContaService serviceConta;

	@Autowired
	private UnidadeService serviceUnidade;

	@Autowired
	private ContaAReceberService service;

	@Autowired
	private ContaAReceberRepository repository;

	private static final String INDEX = "admin/financeiro/consultarContaAReceber";
	private static final String CADASTRO = "admin/financeiro/lancarReceita";


	@RequestMapping("/contaAReceber")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("ContaAReceber", new ContaAReceber());
		view.addObject("listaContas", service.findAll());
		return view;
	}

	@RequestMapping(value = "/contaAReceber", params = { "nomeCR", "boleto" })
	public ModelAndView pesquisar(@RequestParam(value = "nomeCR") String nomeCR,
			@RequestParam(value = "boleto") String boleto) {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("ContaAReceber", new ContaAReceber());
		// Se os valores dos campos forem brancos, lista todos os registros
		if (nomeCR.isEmpty() && boleto.isEmpty()) 
			return findAll();
		else
			view.addObject("listaContas", service.pesquisa(nomeCR, boleto));
		return view;
	}

	@RequestMapping("contaAReceber/edit/{id}")
	public ModelAndView edit(@PathVariable("id") ContaAReceber contaAReceber) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject("contaAReceber", contaAReceber);
		adicionaContaUnidadeNaTela(view);
		return view;
	}

	@RequestMapping("/contaAReceber/novo")
	public ModelAndView save(ContaAReceber contaAReceber) {
		ModelAndView view = new ModelAndView(CADASTRO);
		adicionaContaUnidadeNaTela(view);
		return view;
	}

	@RequestMapping(value = "/contaAReceber/novo", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid ContaAReceber contaAReceber, BindingResult result,
			RedirectAttributes attributes) {
		System.out.println(contaAReceber.getUnidadeCR());
		service.save(contaAReceber);
		return new ModelAndView("redirect:/condominio/contaAReceber");
	}

	@RequestMapping(value = "/contaAReceber/{id}", method = RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView(INDEX);
		service.delete(id);
		view.addObject("ContaAReceber", new ContaAReceber());
		view.addObject("listaContas", service.findAll());
		return view;
	}

	private void adicionaContaUnidadeNaTela(ModelAndView view) {
		view.addObject("unidades", serviceUnidade.findAll());
		view.addObject("contas", serviceConta.findAll());
	}
}
