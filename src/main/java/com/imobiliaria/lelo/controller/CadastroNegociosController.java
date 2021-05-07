
	package com.imobiliaria.lelo.controller;



	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.imobiliaria.lelo.model.Negocio;
import com.imobiliaria.lelo.repository.Negocios;
import com.imobiliaria.lelo.repository.filter.NegocioFiltro;


	@Controller
	@RequestMapping("/home/negocio")
	public class CadastroNegociosController {
		@Autowired
		private Negocios negocios;
		private static final String CADASTRO_VIEW = "Negocios/CadastroNegocios";
		
		@RequestMapping("/novonegocio")
		public ModelAndView novo(@ModelAttribute("filtro") NegocioFiltro filtro, Negocio negocio) {
			ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
			mv.addObject(new Negocio());
			String tipoNegocio = filtro.getTipo() == null ? "" : filtro.getTipo();
			List<Negocio> todosNegocios = negocios.findByTipoContaining(tipoNegocio);
			mv.addObject("negocios", todosNegocios);
			return mv;

		}
		//Lista de negocios com busca
		@RequestMapping("/negocios")
		public ModelAndView pesquisar(@ModelAttribute("filtro") NegocioFiltro filtro) {
			ModelAndView mv = new ModelAndView("Negocios/PesquisaNegocio");
			String tipo = filtro.getTipo() == null ? "" : filtro.getTipo();
			List<Negocio> todosNegocios = negocios.findByTipoContaining(tipo);
			mv.addObject("negocios", todosNegocios);
			return mv;
		}
		//Salvar novo negocio
		@RequestMapping(method = RequestMethod.POST)
		public String salvar(@Validated Negocio negocio, Errors errors, RedirectAttributes attributes) {
			
			try {
				negocios.save(negocio);
				attributes.addFlashAttribute("mensagem", "Negócio salvo com sucesso!");
			}catch(Exception e) {
				if (errors.hasErrors()) {
					attributes.addFlashAttribute("mensagemError", "Negócio precisa ser cadastrado!");
					
				}
				return "redirect:/home/negocio/novonegocio";
			}
			return "redirect:/home/negocio/novonegocio";
		}
		//Editar negócio
		@RequestMapping("/novonegocio/{codigo}")
		public ModelAndView edicao(@PathVariable("codigo") Long codigoNegocio) {
			Negocio negocio = negocios.getOne(codigoNegocio);
			ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
			mv.addObject(negocio);
			return mv;
		}
		
		@RequestMapping(value = "/novonegocio/{codigo}", method = RequestMethod.DELETE)
		public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
			
			try {
				negocios.deleteById(codigo);
				attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
			} catch(Exception e) {
				attributes.addFlashAttribute("mensagemError", "Erro ao excluir, negócio sendo utilizado em outro ambiente!");
			}
			return "redirect:/home/negocio/novonegocio";
		}
	}

	

