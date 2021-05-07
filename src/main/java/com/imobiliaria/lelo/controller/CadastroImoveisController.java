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
import com.imobiliaria.lelo.model.Estado;
import com.imobiliaria.lelo.model.Imovel;
import com.imobiliaria.lelo.model.Negocio;
import com.imobiliaria.lelo.model.Quarto;
import com.imobiliaria.lelo.repository.Categorias;
import com.imobiliaria.lelo.repository.Estados;
import com.imobiliaria.lelo.repository.Imoveis;
import com.imobiliaria.lelo.repository.Negocios;
import com.imobiliaria.lelo.repository.Quartos;

import java.util.List;

@Controller
@RequestMapping("/home/imovel")
public class CadastroImoveisController {

	@Autowired
	private Imoveis imoveis;
	@Autowired
	private Negocios negocios;
	@Autowired
	private Categorias categorias;
	@Autowired
	private Estados estados;
	@Autowired
	private Quartos quartos;
		
	
	private static final String CADASTRO_VIEW = "Imoveis/CadastroImovel";
	
	
	@ModelAttribute("negocios")
	public List<Negocio> todosNegocios() {
		return negocios.findAll();
	}
	@ModelAttribute("categorias")
	public List<Categoria> todasCategorias() {
		return categorias.findAll();
	}
	@ModelAttribute("estados")
	public List<Estado> todosEstados() {
		return estados.findAll();
	}
	
	//Cadastrar novo imóvel e listar
	@RequestMapping("/novoimovel")
	public ModelAndView novo( Imovel imovel) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Imovel());
		
		List<Imovel> todosImoveis = imoveis.findAll();
		mv.addObject("imoveis", todosImoveis);
		List<Quarto> todosQuartos = quartos.findAll();
		mv.addObject("quartos", todosQuartos);
		
		mv.addObject(todosNegocios());
		mv.addObject(todasCategorias());
		mv.addObject(todosEstados());
		return mv;

	}   
	
	//Salvar imóvel
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Imovel imovel, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		imoveis.save(imovel);
		attributes.addFlashAttribute("mensagem", "Imóvel salvo com sucesso!");
		return "redirect:/home/imovel/novoimovel";
	}
	//Editar imóvel
	@RequestMapping("novoimovel/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoImovel) {
		Imovel imovel = imoveis.getOne(codigoImovel);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(imovel);
		
		List<Negocio> todosNegocios = negocios.findAll();
		mv.addObject("negocios", todosNegocios);
		
		List<Categoria> todasCategorias = categorias.findAll();
		mv.addObject("categorias", todasCategorias);
		
		List<Estado> todosEstados = estados.findAll();
		mv.addObject("estados", todosEstados);
		
		List<Quarto> todosQuartos = quartos.findAll();
		mv.addObject("quartos", todosQuartos);
		return mv;
	}
	//Deletar imóvel
	@RequestMapping(value="novoimovel/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		try {
			imoveis.deleteById(codigo);
			attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
			
		}catch(Exception e) {
		attributes.addFlashAttribute("mensagemError", "Erro ao excluir, dados sendo utilizados em outro ambiente!");
		
		} 
	
		return "redirect:/home/imovel/novoimovel";
	}
}
