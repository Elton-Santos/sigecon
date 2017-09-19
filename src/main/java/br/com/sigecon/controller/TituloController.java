package br.com.sigecon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sigecon.models.financeiro.Titulo;
import br.com.sigecon.service.TituloService;

@Controller
@RequestMapping("/titulo")
public class TituloController {

	private final String INDEX = "titulos/cadastroTitulo";

	@Autowired
	private TituloService service;

	@GetMapping("/add")
	public ModelAndView add(Titulo titulo) {
		ModelAndView view = new ModelAndView(INDEX);
		view.addObject("listaTitulos", titulo);
		return view;
	}

}
