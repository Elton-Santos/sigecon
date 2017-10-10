package br.com.sigecon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sigecon.models.entidades.PessoaFisica;
import br.com.sigecon.repositoty.PessoaFisicaRepository;
import br.com.sigecon.service.PessoaFisicaService;

@Controller
@RequestMapping("/fisica")
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService service;

	@Autowired
	private PessoaFisicaRepository repository;

	private static final String INDEX = "entidades/listaPessoaFisica";
	private static final String CADASTRO = "entidades/cadastroPessoa";

	@RequestMapping("/pesquisaPessoa")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("PessoaFisica", new PessoaFisica());
		view.addObject("listaPessoas", service.findAll());
		return view;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") PessoaFisica pessoaFisica) {
		ModelAndView view = new ModelAndView(CADASTRO);
		view.addObject(pessoaFisica);
		return view;
	}

	@RequestMapping("/novo")
	public ModelAndView save(PessoaFisica pessoaFisica) {
		return new ModelAndView(CADASTRO);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid PessoaFisica pessoaFisica, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return save(pessoaFisica);
		}

		service.save(pessoaFisica);
		attributes.addFlashAttribute("mensagem", "Entidade salva com sucesso.");
		return new ModelAndView("redirect:/fisica/novo");
	}

	@RequestMapping
	public ModelAndView pesquisar(PessoaFisica pessoaFisica, @PageableDefault(size = 6) Pageable pageable,
			HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView(INDEX);
		PageWrapper<PessoaFisica> paginaWrapper = new PageWrapper<>(
				repository.porNome(pessoaFisica.getNome(), pageable), httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/fisica";
	}
}
