package com.sycon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sycon.model.Pessoa;
import com.sycon.model.User;
import com.sycon.service.PessoaService;
import com.sycon.service.UserService;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PessoaService servicePessoa;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		Pessoa pessoa = new Pessoa();
		modelAndView.addObject("pessoa", pessoa);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Pessoa pessoa, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(pessoa.getUser().getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "Usuário já cadastrado com este email");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			servicePessoa.save(pessoa);
			modelAndView.addObject("successMessage", "Usuário cadastrado com sucesso");
			modelAndView.addObject("pessoa", new Pessoa());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Pessoa pessoa = new Pessoa();
		pessoa.setUser(userService.findUserByEmail(auth.getName()));
		modelAndView.addObject("userName",
				"Bem vindo " + pessoa.getNome() + " (" + pessoa.getUser().getEmail() + ")");
		modelAndView.addObject("adminMessage", "Conteúdo exclusivo para usuário administrador");
		modelAndView.setViewName("admin/index");
		return modelAndView;
	}

}
