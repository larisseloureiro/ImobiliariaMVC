package com.gft.starters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.starters.model.Linguagem;
import com.gft.starters.repository.LinguagemRepository;

@Controller
@RequestMapping("/linguagem")
public class LinguagemController {

	@Autowired
	LinguagemRepository linguagemRepository;
	
	@RequestMapping(value = "/novo" , method = RequestMethod.GET)
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("linguagem/Cadastro");
		mv.addObject("linguagem", new Linguagem());
		return mv;
	}
	
	@RequestMapping(value = "/novo" , method = RequestMethod.POST)
	public String processarCadastro(Linguagem linguagem, RedirectAttributes redirAttrs) {
		linguagemRepository.save(linguagem);
		if(linguagem.getId()!=null) {
			redirAttrs.addFlashAttribute("mensagem", "Salvo com sucesso");
			redirAttrs.addFlashAttribute("tipoAlerta", "success");
		}
		else
		{
			redirAttrs.addFlashAttribute("mensagem", "Não salvou não");
			redirAttrs.addFlashAttribute("tipoAlerta", "danger");
		}
				
		return "redirect:/linguagem/listar";
	}
	

	@RequestMapping(value = "/listar" , method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("linguagem/Lista");
		List<Linguagem> listaStaters = linguagemRepository.findAll();
		mv.addObject("lista", listaStaters);
		return mv;
	}
	
		
	@RequestMapping(value = "/excluir/{codigo}" , method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes redirAttrs) {
		try {
			linguagemRepository.deleteById(codigo);
			redirAttrs.addFlashAttribute("mensagem", "Linguagem excluído com sucesso");
			redirAttrs.addFlashAttribute("tipoAlerta", "success");
		}catch(Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("mensagem", "Deu algum erro");
			redirAttrs.addFlashAttribute("tipoAlerta", "danger");
		}
		
		return "redirect:/linguagem/listar";
	}
	
	
	@RequestMapping(value = "/novo/{codigo}" , method = RequestMethod.GET)
	public String editar(@PathVariable("codigo") Long codigo, Model model) {
		Optional<Linguagem> linguagem = linguagemRepository.findById(codigo);
		if(linguagem.isPresent()) {
			model.addAttribute("linguagem",linguagem.get());
		}
		
		return "linguagem/Cadastro";
	}
	
}
