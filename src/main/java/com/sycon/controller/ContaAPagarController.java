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

import com.sycon.model.ContaAPagar;
import com.sycon.repository.ContaAPagarRepository;
import com.sycon.service.ContaAPagarService;
import com.sycon.service.ContaService;
import com.sycon.service.PessoaService;

@RestController
@RequestMapping("/condominio")
public class ContaAPagarController {

	@Autowired
	private ContaService serviceConta;

	@Autowired
	private PessoaService servicePessoa;

	@Autowired
	private ContaAPagarService service;

	@Autowired
	private ContaAPagarRepository repository;

	private static final String INDEX = "admin/financeiro/consultarContaAPagar";
	private static final String CADASTRO = "admin/financeiro/lancarDespesas";

	// criar um controller para financeiro
	@RequestMapping("/contas")
	public ModelAndView financeiro() {
		ModelAndView view = new ModelAndView("admin/consultas/financeiro");
		return view;
	}

	@RequestMapping("/contaAPagar")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("ContaAPagar", new ContaAPagar());
		view.addObject("listaContas", service.findAll());
		return view;
	}

	@RequestMapping(value = "/contaAPagar", params = { "nomeCP", "notaFiscal" })
	public ModelAndView pesquisar(@RequestParam(value = "nomeCP") String nomeCP,
			@RequestParam(value = "notaFiscal") String notaFiscal) {
		ModelAndView view = new ModelAndView(INDEX);
		System.out.println(service.pesquisa(nomeCP, notaFiscal).get(0).getNomeCP());
		
		view.addObject("ContaAPagar", new ContaAPagar());
		// Se os valores dos campos forem brancos, lista todos os registros
		if (nomeCP.isEmpty() && notaFiscal.isEmpty())
			return findAll();
		else
			view.addObject("listaContas", service.pesquisa(nomeCP, notaFiscal));
		return view;
	}

	@RequestMapping("contaAPagar/edit/{id}")
	public ModelAndView edit(@PathVariable("id") ContaAPagar contaAPagar) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject("contaAPagar", contaAPagar);
		adicionaContaPessoasNaTela(view);
		return view;
	}

	@RequestMapping("/contaAPagar/novo")
	public ModelAndView save(ContaAPagar contaAPagar) {
		ModelAndView view = new ModelAndView(CADASTRO);
		adicionaContaPessoasNaTela(view);
		return view;
	}

	@RequestMapping(value = "/contaAPagar/novo", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid ContaAPagar contaAPagar, BindingResult result,
			RedirectAttributes attributes) {
		System.out.println(">>>>>>>>>>>>>>>" + contaAPagar.getDtPagamentoCP());
		service.save(contaAPagar);
		return new ModelAndView("redirect:/condominio/contaAPagar");
	}

	@RequestMapping(value = "/contaAPagar/{id}", method = RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView(INDEX);
		service.delete(id);
		view.addObject("ContaAPagar", new ContaAPagar());
		view.addObject("listaContas", service.findAll());
		return view;
	}

	private void adicionaContaPessoasNaTela(ModelAndView view) {
		view.addObject("pessoas", servicePessoa.findAll());
		view.addObject("contas", serviceConta.findAll());
	}
}
