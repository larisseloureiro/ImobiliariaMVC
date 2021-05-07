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

import com.imobiliaria.lelo.model.Quarto;
import com.imobiliaria.lelo.repository.Quartos;

import com.imobiliaria.lelo.repository.filter.QuartoFiltro;

@Controller
@RequestMapping("/home/quarto")
public class CadastroQuartoController {

	@Autowired
	private Quartos quartos;
	private static final String CADASTRO_VIEW = "Quartos/CadastroQuarto";

	@RequestMapping("/cadquarto")
	public ModelAndView novo(@ModelAttribute("filtro") QuartoFiltro filtro, Quarto quarto) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Quarto());
		String qntd = filtro.getQntd() == null ? "" : filtro.getQntd();
		List<Quarto> todosQuartos = quartos.findByQntdContaining(qntd);
		mv.addObject("quartos", todosQuartos);
		return mv;
	}

	// Lista de negocios com busca
	@RequestMapping("/quartos")
	public ModelAndView pesquisar(@ModelAttribute("filtro") QuartoFiltro filtro) {
		ModelAndView mv = new ModelAndView("Quartos/PesquisaQuarto");
		String qntd = filtro.getQntd() == null ? "" : filtro.getQntd();
		List<Quarto> todosQuartos = quartos.findByQntdContaining(qntd);
		mv.addObject("quartos", todosQuartos);
		return mv;
	}

	// Salvar novo negocio
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Quarto quarto, Errors errors, RedirectAttributes attributes) {
		try {
			quartos.save(quarto);
			attributes.addFlashAttribute("mensagem", "Quantidade de quarto salvo com sucesso!");

		} catch (Exception e) {
			if (errors.hasErrors()) {
				attributes.addFlashAttribute("mensagemError", "Quantidade de quarto precisa ser cadastrado!");

			}
			return "redirect:/home/quarto/cadquarto";

		}
		return "redirect:/home/quarto/cadquarto";
	}

	// Editar negócio
	@RequestMapping("/cadquarto/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoQuarto) {
		Quarto quarto = quartos.getOne(codigoQuarto);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(quarto);
		return mv;
	}

	@RequestMapping(value = "/cadquarto/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			quartos.deleteById(codigo);
			attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemError",
					"Erro ao excluir, quantidade de quarto cadastro sendo utilizado em outro ambiente!");
		}

		return "redirect:/home/quarto/cadquarto";
	}

}
