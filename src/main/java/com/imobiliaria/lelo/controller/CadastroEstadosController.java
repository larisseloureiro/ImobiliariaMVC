package com.imobiliaria.lelo.controller;

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


import com.imobiliaria.lelo.model.Estado;

import com.imobiliaria.lelo.repository.Estados;


import com.imobiliaria.lelo.repository.filter.EstadoFiltro;

import java.util.List;

@Controller
@RequestMapping("/home/estado")
public class CadastroEstadosController {

	
	@Autowired
	private Estados estados;
	
	private static final String CADASTRO_VIEW = "Estados/CadastroEstados";

	@RequestMapping("/novoestado")
	public ModelAndView novo(@ModelAttribute("filtro") EstadoFiltro filtro, Estado estado) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Estado());
		String tipo = filtro.getState() == null ? "" : filtro.getState();
		List<Estado> todosEstados = estados.findByStateContaining(tipo);
		mv.addObject("estados", todosEstados);
		return mv;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Estado estado, Errors errors, RedirectAttributes attributes) {
		try {
			estados.save(estado);
			attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");
		}catch(Exception e) {
			if(errors.hasErrors()){
				attributes.addFlashAttribute("mensagemError", "Estado precisa ser cadastrado!");
			}
			return "redirect:/home/estado/novoestado";
		}
		
		
		return "redirect:/home/estado/novoestado";
	}

	@RequestMapping("/estados")
	public ModelAndView pesquisar(@ModelAttribute("filtro") EstadoFiltro filtro) {
		ModelAndView mv = new ModelAndView("Estados/PesquisaEstado");
		String tipo = filtro.getState() == null ? "" : filtro.getState();
		List<Estado> todosEstados = estados.findByStateContaining(tipo);
		mv.addObject("estados", todosEstados);
		return mv;
	}

	

	@RequestMapping("novoestado/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoEstado) {
		Estado estado = estados.getOne(codigoEstado);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(estado);
		
		List<Estado> todosEstados = estados.findAll();
		mv.addObject("estados", todosEstados);
		return mv;
	}

	@RequestMapping(value="novoestado/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		try {
			estados.deleteById(codigo);
			attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
			
		}catch(Exception e) {
		attributes.addFlashAttribute("mensagemError", "Erro ao excluir, estado sendo utilizada em outro ambiente!");
		
		} 
		
		return "redirect:/home/estado/novoestado";
	}
}
