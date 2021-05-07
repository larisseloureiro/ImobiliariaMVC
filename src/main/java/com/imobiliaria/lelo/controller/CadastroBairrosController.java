package com.imobiliaria.lelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imobiliaria.lelo.model.Bairro;
import com.imobiliaria.lelo.model.Estado;
import com.imobiliaria.lelo.model.Municipio;
import com.imobiliaria.lelo.repository.Bairros;
import com.imobiliaria.lelo.repository.Estados;
import com.imobiliaria.lelo.repository.Municipios;
import com.imobiliaria.lelo.repository.filter.BairroFiltro;

import java.util.List;

@Controller
@RequestMapping("/home/bairro")
public class CadastroBairrosController {

	@Autowired
	private Estados estados;
	@Autowired
	private Municipios municipios;
	@Autowired
	private Bairros bairros;
	private static final String CADASTRO_VIEW = "Bairros/CadastroBairro";

	@RequestMapping("/novobairro")
	public ModelAndView novo(@ModelAttribute("filtro") BairroFiltro filtro, Bairro bairro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Bairro());
		String tipo = filtro.getNomeBairro() == null ? "" : filtro.getNomeBairro();
		List<Bairro> todosBairros = bairros.findBynomeBairroContaining(tipo);
		mv.addObject("bairros", todosBairros);
		List<Municipio> todosMunicipios = municipios.findAll();
		mv.addObject("municipios", todosMunicipios);
		List<Estado> todosEstados = estados.findAll();
		mv.addObject("estados", todosEstados);

		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Bairro bairro, Errors errors, RedirectAttributes attributes) {

		try {

			bairros.save(bairro);
			attributes.addFlashAttribute("mensagem", "Bairro salvo com sucesso!");
		} catch (Exception e) {
			if (errors.hasErrors()) {
				attributes.addFlashAttribute("mensagemError", "Bairro precisa ser cadastrado!");

			}
			return "redirect:/home/bairro/novobairro";
		}
		return "redirect:/home/bairro/novobairro";

	}

	@RequestMapping("/bairros")
	public ModelAndView pesquisar(@ModelAttribute("filtro") BairroFiltro filtro) {
		ModelAndView mv = new ModelAndView("Bairros/PesquisaBairro");
		String tipo = filtro.getNomeBairro() == null ? "" : filtro.getNomeBairro();
		List<Bairro> todosBairros = bairros.findBynomeBairroContaining(tipo);
		mv.addObject("bairros", todosBairros);
		return mv;
	}

	@RequestMapping("novobairro/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoBairro) {
		Bairro bairro = bairros.getOne(codigoBairro);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(bairro);

		List<Estado> todosEstados = estados.findAll();
		mv.addObject("estados", todosEstados);

		List<Municipio> listaMunicipios = municipios.findByEstado_Codigo(codigoBairro);
		mv.addObject("municipios", listaMunicipios);

		return mv;

	}

	@RequestMapping(value = "novobairro/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		bairros.deleteById(codigo);
		attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		return "redirect:/home/bairro/novobairro";
	}

	@ResponseBody
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Municipio> listarMunicipioPorEstado(Long codigoEstado) {
		List<Municipio> listaMunicipios = municipios.findByEstado_Codigo(codigoEstado);
		return listaMunicipios;
	}
}
