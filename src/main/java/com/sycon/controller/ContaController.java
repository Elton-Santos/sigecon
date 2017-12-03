package com.sycon.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

import com.sycon.model.Conta;
import com.sycon.repository.ContaRepository;
import com.sycon.service.CondominioService;
import com.sycon.service.ContaAPagarService;
import com.sycon.service.ContaService;
import com.sycon.service.PessoaService;

@RestController
@RequestMapping("/condominio")
public class ContaController {

	@Autowired
	private ContaService service;

	@Autowired
	private PessoaService servicePessoa;

	@Autowired
	private CondominioService serviceCondominio;

	@Autowired
	private ContaAPagarService serviceCP;

	@Autowired
	private ContaRepository repository;

	private static final String INDEX = "admin/financeiro/consultarConta";
	private static final String CADASTRO = "admin/financeiro/cadastrarContas";
	private static final String RELATORIO = "admin/financeiro/relatorioConta";

	@RequestMapping(value = "/relatorio")
	public ModelAndView relatorio() {
		ModelAndView view = new ModelAndView(RELATORIO);
		view.addObject("Conta", new Conta());
		view.addObject("listaContas", service.calcularTotalDespesas(service.findAll()));
		view.addObject("condominioContas", serviceCondominio.findAll());
		view.addObject("listaPessoas", servicePessoa.findAll());
		return view;
	}

	@RequestMapping("/conta")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("Conta", new Conta());
		view.addObject("listaContas", service.calcularTotalDespesas(service.findAll()));
		view.addObject("condominioContas", serviceCondominio.findAll());

		return view;
	}

	@RequestMapping(value = "/conta", params = { "descricaoConta", "condominioConta", "ano", "mes" })
	public ModelAndView pesquisar(@RequestParam(value = "descricaoConta") String descricaoConta,
			@RequestParam(value = "condominioConta") long idCondominio, @RequestParam("ano") int ano,
			@RequestParam("mes") int mes) {
		ModelAndView view = new ModelAndView(INDEX);
		Conta conta = new Conta();
		Date calStart = new GregorianCalendar(ano, mes, 1).getTime();
		Date calEnd = new GregorianCalendar(ano, mes, calcularUltimoDiaDoMes(mes)).getTime();
		conta.setCondominioConta(serviceCondominio.findOne(idCondominio));
		conta.setDescricaoConta(descricaoConta);
		view.addObject("Conta", conta);
		System.out.println(descricaoConta);
		// Se os valores dos campos forem brancos, lista todos os registros
		if (descricaoConta.isEmpty() && idCondominio == 0)
			return findAll();
		else
			view.addObject("listaContas", service.calcularTotalDespesas(service.pesquisa(descricaoConta, serviceCondominio.findOne(idCondominio)),calStart, calEnd));
		view.addObject("condominioContas", serviceCondominio.findAll());
		return view;
	}

	@RequestMapping("conta/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Conta conta) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject("conta", conta);
		return view;
	}

	@RequestMapping("/conta/novo")
	public ModelAndView save(Conta conta) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject("condominioContas", serviceCondominio.findAll());
		return view;
	}

	@RequestMapping(value = "/conta/novo", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Conta conta, BindingResult result, RedirectAttributes attributes) {
		service.save(conta);
		return new ModelAndView("redirect:/condominio/conta");
	}

	@RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable("id") Long id) {
		return "redirect:/condominio/conta";
	}

	@RequestMapping(value="/conta/gerarRateio",params={"descricaoConta","condominioConta","meshidden","anohidden"})

	public ModelAndView gerarRateio(@RequestParam(value = "descricaoConta") String descricaoConta,
			@RequestParam(value = "condominioConta") long idCondominio, @RequestParam("anohidden") int ano,
			@RequestParam("meshidden") int mes) {
		ModelAndView view = new ModelAndView(INDEX);
		Date calStart = new GregorianCalendar(ano, mes, 1).getTime();
		Date calEnd = new GregorianCalendar(ano, mes, calcularUltimoDiaDoMes(mes)).getTime();
		if (idCondominio != 0)
			service.gerarRateio(service.pesquisa(descricaoConta, serviceCondominio.findOne(idCondominio)), calStart, calEnd);
		view.addObject("Conta", new Conta());
		view.addObject("listaContas", service
				.calcularTotalDespesas(service.pesquisa(descricaoConta, serviceCondominio.findOne(idCondominio)), calStart, calEnd));
		view.addObject("condominioContas", serviceCondominio.findAll());

		return view;
	}

	int calcularUltimoDiaDoMes(int mes) {
		int result = 0;
		switch (mes) {
		case 0:
			result=31;
			break;
		case 1:
			result=28;
			break;
		case 2:
			result=31;
			break;
		case 3:
			result=30;
			break;
		case 4:
			result=31;
			break;
		case 5:
			result=30;
			break;
		case 6:
			result=31;
			break;
		case 7:
			result=31;
			break;
		case 8:
			result=30;
			break;
		case 9:
			result=31;
			break;
		case 10:
			result=30;
			break;
		case 11:
			result=31;
			break;
		}
		return result;	
	}

}
