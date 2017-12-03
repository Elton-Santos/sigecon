package com.sycon.controller;

import java.util.List;
import java.util.Set;

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

import com.sycon.model.Condominio;
import com.sycon.model.Pessoa;
import com.sycon.model.Unidade;
import com.sycon.repository.CondominioRepository;
import com.sycon.service.CondominioService;
import com.sycon.service.UnidadeService;

@RestController
@RequestMapping("/condominio")
public class CondominioController {

	@Autowired
	private CondominioService service;
	
	@Autowired
	private CondominioRepository repository;

	private static final String INDEX = "admin/consultas/consultaCondominio";
	private static final String CADASTRO = "admin/cadastros/cadastrarCondominio";

	@RequestMapping("/condominio")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("condominioPesquisa", new Condominio());
		view.addObject("condominio", new Condominio());
		view.addObject("listaCondominio", service.findAll());
		return view;
	}
	
	@RequestMapping(value = "/condominio", params = {"nomeCondominio","cnpj"})
	public ModelAndView pesquisar(@RequestParam(value = "nomeCondominio") String nomeCondominio,@RequestParam(value = "cnpj") String cnpj) {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("condominioPesquisa", new Condominio());
		//Se os valores dos campos forem brancos, lista todos os registros
		if(nomeCondominio.isEmpty()&&cnpj.isEmpty())
			return findAll();
		else
			view.addObject("listaCondominio", service.pesquisa(cnpj, nomeCondominio));
		return view;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject(repository.findOne(id));
		return view;
	}

	@RequestMapping("/novo")
	public ModelAndView save(Condominio condominio) {
		return new ModelAndView(CADASTRO);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Condominio condominio, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return save(condominio);
		}

		service.save(condominio);
		attributes.addFlashAttribute("mensagem", "Condominio salva com sucesso.");
		return new ModelAndView("redirect:/condominio/novo");
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView(INDEX);
		service.delete(id);
		view.addObject("condominioPesquisa", new Condominio());
		view.addObject("condominio", new Condominio());
		view.addObject("listaCondominio", service.findAll());
		return view;
	}

	// @RequestMapping("/condominio")
	// public ModelAndView pesquisar(Condominio condominio, @PageableDefault(size =
	// 6) Pageable pageable,
	// HttpServletRequest httpServletRequest) {
	//
	// ModelAndView mv = new ModelAndView(INDEX);
	// PageWrapper<Condominio> paginaWrapper = new PageWrapper<>(
	// repository.porNome(condominio.getNomeCondominio(), pageable),
	// httpServletRequest);
	//
	// mv.addObject("pagina", paginaWrapper);
	// return mv;
	// }


}
