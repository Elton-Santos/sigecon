package com.sycon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sycon.model.Condominio;
import com.sycon.model.Unidade;
import com.sycon.repository.UnidadeRepository;
import com.sycon.service.CondominioService;
import com.sycon.service.UnidadeService;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

	@Autowired
	private UnidadeService service;
	
	@Autowired
	private CondominioService serviceCondominio;

	@Autowired
	private UnidadeRepository repository;

	private static final String INDEX = "admin/consultas/consultaUnidade";
	private static final String CADASTRO = "admin/cadastros/cadastrarUnidade";

	@RequestMapping("/unidade")
	public ModelAndView findAll() {

		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("consultaUnidade", new Unidade());
		view.addObject("listaUnidade", service.findAll());

		return view;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject("condominios", serviceCondominio.findAll());
		view.addObject(repository.findOne(id));
		return view;
	}

	@RequestMapping("/novo")
	public ModelAndView save(Unidade unidade) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject("condominios", serviceCondominio.findAll());
		return view;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Unidade unidade, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return save(unidade);
		}

		service.save(unidade);
		attributes.addFlashAttribute("mensagem", "Condominio salva com sucesso.");
		return new ModelAndView("redirect:/unidade/novo");
	}


	// @RequestMapping
	// public ModelAndView pesquisar(Unidade unidade, @PageableDefault(size = 6)
	// Pageable pageable,
	// HttpServletRequest httpServletRequest) {
	//
	// ModelAndView view = new ModelAndView(INDEX);
	// PageWrapper<Unidade> paginaWrapper = new
	// PageWrapper<>(repository.porNome(unidade.getUnidade(), pageable),
	// httpServletRequest);
	//
	// view.addObject("pagina", paginaWrapper);
	// return view;
	// }

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) public String
	 * excluir(@PathVariable("id") Long id) { service.delete(id); return
	 * "redirect:/unidade"; }
	 */
}
