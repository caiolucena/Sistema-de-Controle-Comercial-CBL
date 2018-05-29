package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Estabelecimento;
import com.cbl.erp.repository.Cidades;
import com.cbl.erp.repository.Estados;

@Controller
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {


	@Autowired
	Estados estados;
	
	@Autowired
	Cidades cidades;	
	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estabelecimento estabelecimento) {

		ModelAndView mv = new ModelAndView("estabelecimento/CadastroEstabelecimento");
		mv.addObject("cidades", cidades.findAll());
		mv.addObject("estados", estados.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView cadastro(@Valid Estabelecimento estabelecimento,BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			System.out.println("Cidade Selecionada"+estabelecimento.getEndereco().getCidade().getNome());
			return novo(estabelecimento);
		}
		attributes.addFlashAttribute("mensagem", "Estabelecimento salvo com sucesso!");
		
		return new ModelAndView("redirect:/estabelecimento/novo");
		
	}
}
