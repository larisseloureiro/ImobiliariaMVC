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

import com.gft.starters.model.Framework;
import com.gft.starters.model.Linguagem;
import com.gft.starters.model.Starter;
import com.gft.starters.repository.FrameworkRepository;
import com.gft.starters.repository.LinguagemRepository;
import com.gft.starters.repository.StarterRepository;

@Controller
@RequestMapping("/starter")
public class StarterController {
	
	@Autowired
	StarterRepository starterRepository;
	
	@Autowired
	LinguagemRepository linguagemRepository;
	
	@Autowired
	FrameworkRepository frameworkRepository;
	
	@RequestMapping(value = "/novo" , method = RequestMethod.GET)
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("starter/Cadastro");
		
		List<Linguagem> linguagens = linguagemRepository.findAll();
		
		mv.addObject("starter", new Starter());
		mv.addObject("listaLinguagens", linguagens);
		
		return mv;
	}
	
	@RequestMapping(value = "/novo" , method = RequestMethod.POST)
	public String processarCadastro(Starter starter, RedirectAttributes redirAttrs) {
		starterRepository.save(starter);
		if(starter.getId()!=null) {
			redirAttrs.addFlashAttribute("mensagem", "Salvo com sucesso");
			redirAttrs.addFlashAttribute("tipoAlerta", "success");
		}
		else
		{
			redirAttrs.addFlashAttribute("mensagem", "Não salvou não");
			redirAttrs.addFlashAttribute("tipoAlerta", "danger");
		}
				
		return "redirect:/starter/listar";
	}
	

	@RequestMapping(value = "/listar" , method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("starter/Lista");
		List<Starter> listaStaters = starterRepository.findAll();
		mv.addObject("lista", listaStaters);
		return mv;
	}
	
		
	@RequestMapping(value = "/excluir/{codigo}" , method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes redirAttrs) {
		try {
			starterRepository.deleteById(codigo);
			redirAttrs.addFlashAttribute("mensagem", "Starter excluído com sucesso");
			redirAttrs.addFlashAttribute("tipoAlerta", "success");
		}catch(Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("mensagem", "Deu algum erro");
			redirAttrs.addFlashAttribute("tipoAlerta", "danger");
		}
		
		return "redirect:/starter/listar";
	}
	
	
	@RequestMapping(value = "/novo/{codigo}" , method = RequestMethod.GET)
	public String editar(@PathVariable("codigo") Long codigo, Model model) {
		
		Optional<Starter> starter = starterRepository.findById(codigo);
		if(starter.isPresent()) {
			model.addAttribute("starter",starter.get());
		}
		
		List<Linguagem> linguagens = linguagemRepository.findAll();
		model.addAttribute("listaLinguagens", linguagens);
		
		Long idLinguagem = starter.get().getFramework().getLinguagem().getId();
		List<Framework> frameworks = frameworkRepository.findByLinguagem_Id(idLinguagem);
		
		model.addAttribute("listaFrameworks", frameworks);
		
		return "starter/Cadastro";
		
	}

}
