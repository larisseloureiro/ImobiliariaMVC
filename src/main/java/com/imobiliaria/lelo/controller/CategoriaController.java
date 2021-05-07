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

import com.imobiliaria.lelo.model.Categoria;
import com.imobiliaria.lelo.repository.Categorias;
import com.imobiliaria.lelo.repository.filter.CategoriaFiltro;

import java.util.List;

@Controller
@RequestMapping("/home")
public class CategoriaController {

	@Autowired
	private Categorias categorias;
	private static final String CADASTRO_VIEW = "Categorias/CadastroCategoria";

	@RequestMapping("/categorias")
	public ModelAndView novo(@ModelAttribute("filtro") CategoriaFiltro filtro, Categoria categoria) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Categoria());
		String tipo = filtro.getTipo() == null ? "" : filtro.getTipo();
		List<Categoria> todasCategorias = categorias.findByTipoContaining(tipo);
		mv.addObject("categorias", todasCategorias);
		return mv;

	}

	@RequestMapping("/pesquisarcategorias")
	public ModelAndView pesquisar(@ModelAttribute("filtro") CategoriaFiltro filtro) {
		ModelAndView mv = new ModelAndView("Categorias/PesquisaCategoria");
		String tipo = filtro.getTipo() == null ? "" : filtro.getTipo();
		List<Categoria> todasCategorias = categorias.findByTipoContaining(tipo);
		mv.addObject("categorias", todasCategorias);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {

		try {
			
			categorias.save(categoria);
			attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
		}catch(Exception e ) {

			if (errors.hasErrors()) {
				attributes.addFlashAttribute("mensagemError", "Categoria precisa ser cadastrada!");
							
			}
			return "redirect:/home/categorias";
		}
		
		
		return "redirect:/home/categorias";
	}

	@RequestMapping("categorias/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoCategoria) {
		Categoria categoria = categorias.getOne(codigoCategoria);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(categoria);
		return mv;
	}

	@RequestMapping(value = "/categorias/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		try {
			categorias.deleteById(codigo);
			attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
			
		}catch(Exception e) {
		attributes.addFlashAttribute("mensagemError", "Erro ao excluir, categoria sendo utilizada em outro ambiente!");
		
		} 
			return "redirect:/home/categorias";
		
	}

}
