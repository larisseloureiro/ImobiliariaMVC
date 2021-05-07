package com.imobiliaria.lelo.controller;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/home")
public class PgInicialController {
	
	private static final String CADASTRO_VIEW = "PgInicial";
	
	@RequestMapping()
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);

		
		return mv;

	}
}
