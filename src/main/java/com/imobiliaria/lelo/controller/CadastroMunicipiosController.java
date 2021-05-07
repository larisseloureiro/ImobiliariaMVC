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
import com.imobiliaria.lelo.model.Municipio;
import com.imobiliaria.lelo.repository.Estados;
import com.imobiliaria.lelo.repository.Municipios;

import com.imobiliaria.lelo.repository.filter.MunicipioFiltro;

import java.util.List;

@Controller
@RequestMapping("/home/municipio")
public class CadastroMunicipiosController {

	@Autowired
	private Estados estados;

	@Autowired
	private Municipios municipios;
	private static final String CADASTRO_VIEW = "Municipios/CadastroMunicipio";

	@RequestMapping("/novomunicipio")
	public ModelAndView novo(Municipio municipio) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Municipio());

		List<Municipio> todosMunicipios = municipios.findAll();
		mv.addObject("municipios", todosMunicipios);
		mv.addObject(todosEstados());
		return mv;

	}

	@ModelAttribute("estados")
	public List<Estado> todosEstados() {
		return estados.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Municipio municipio, Errors errors, RedirectAttributes attributes) {
		try {
			municipios.save(municipio);
			attributes.addFlashAttribute("mensagem", "Município salvo com sucesso!");
		} catch (Exception e) {
			if (errors.hasErrors()) {
				attributes.addFlashAttribute("mensagemError", "Município precisa ser cadastrado!");
			}
			return "redirect:/home/municipio/novomunicipio";
		}
		return "redirect:/home/municipio/novomunicipio";

	}

	@RequestMapping("/municipios")
	public ModelAndView pesquisar(@ModelAttribute("filtro") MunicipioFiltro filtro) {
		ModelAndView mv = new ModelAndView("Municipios/PesquisaMunicipio");
		String tipo = filtro.getNomeMunicipio() == null ? "" : filtro.getNomeMunicipio();
		List<Municipio> todosMunicipios = municipios.findBynomeMunicipioContaining(tipo);
		mv.addObject("municipios", todosMunicipios);
		return mv;
	}

	@RequestMapping("novomunicipio/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoMunicipio) {
		Municipio municipio = municipios.getOne(codigoMunicipio);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(municipio);

		List<Estado> todosEstados = estados.findAll();
		mv.addObject("estados", todosEstados);
		return mv;
	}

	@RequestMapping(value = "novomunicipio/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {

		try {
			municipios.deleteById(codigo);
			attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemError",
					"Erro ao excluir, município sendo utilizado em outro ambiente!");
		}
		return "redirect:/home/municipio/novomunicipio";
	}

}
