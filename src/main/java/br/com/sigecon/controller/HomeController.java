package br.com.sigecon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView login() {
		ModelAndView view = new ModelAndView("pages/login");
		return view;
	}

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("pages/home");
		return view;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		return view;
	}

}
