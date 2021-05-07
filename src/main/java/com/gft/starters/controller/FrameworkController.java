package com.gft.starters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.starters.model.Framework;
import com.gft.starters.model.Linguagem;
import com.gft.starters.repository.FrameworkRepository;
import com.gft.starters.repository.LinguagemRepository;

@Controller
@RequestMapping("/framework")
public class FrameworkController {
	
	@Autowired
	FrameworkRepository frameworkRepository;
	
	@Autowired
	LinguagemRepository linguagemRepository;
	
	@RequestMapping(value = "/novo" , method = RequestMethod.GET)
	public ModelAndView cadastro() {
		
		List<Linguagem> listaLinguagens = linguagemRepository.findAll();
		
		ModelAndView mv = new ModelAndView("framework/Cadastro");
		
		mv.addObject("framework", new Framework());
		mv.addObject("lista", listaLinguagens);
		
		return mv;
	}
	
	@RequestMapping(value = "/novo" , method = RequestMethod.POST)
	public String processarCadastro(Framework framework, RedirectAttributes redirAttrs) {
		frameworkRepository.save(framework);
		
		if(framework.getId()!=null) {
			redirAttrs.addFlashAttribute("mensagem", "Salvo com sucesso");
			redirAttrs.addFlashAttribute("tipoAlerta", "success");
		}
		else
		{
			redirAttrs.addFlashAttribute("mensagem", "Não salvou não");
			redirAttrs.addFlashAttribute("tipoAlerta", "danger");
		}
				
		return "redirect:/framework/listar";
	}
	

	@RequestMapping(value = "/listar" , method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("framework/Lista");
		List<Framework> listaStaters = frameworkRepository.findAll();
		mv.addObject("lista", listaStaters);
		return mv;
	}
	
		
	@RequestMapping(value = "/excluir/{codigo}" , method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes redirAttrs) {
		try {
			frameworkRepository.deleteById(codigo);
			redirAttrs.addFlashAttribute("mensagem", "Framework excluído com sucesso");
			redirAttrs.addFlashAttribute("tipoAlerta", "success");
		}catch(Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("mensagem", "Deu algum erro");
			redirAttrs.addFlashAttribute("tipoAlerta", "danger");
		}
		
		return "redirect:/framework/listar";
	}
	
	
	@RequestMapping(value = "/novo/{codigo}" , method = RequestMethod.GET)
	public String editar(@PathVariable("codigo") Long codigo, Model model) {
		Optional<Framework> framework = frameworkRepository.findById(codigo);
		if(framework.isPresent()) {
			model.addAttribute("framework",framework.get());
		}
		
		return "framework/Cadastro";
	}
	
	@RequestMapping(value = "/listarPorLinguagem" , method = RequestMethod.GET)
	@ResponseBody
	public List<Framework> listarFrameworksPorLinguagem(Long idLinguagem){
		/**
		 * Maneira original:
		 */
		
		//List<Framework> listaFrameworks = frameworkRepository.findByLinguagem_Id(idLinguagem);
		
		
		/**
		 * Nova maneira:
		 */
		
		List<Framework> listaFrameworks = linguagemRepository.findById(idLinguagem).get().getFrameworks();
		
		return listaFrameworks;
	}
		
}
