package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Endereco;
import com.cbl.erp.model.Estabelecimento;

@Controller
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

	@RequestMapping("/novo")
	public ModelAndView novo(Estabelecimento estabelecimento) {

		ModelAndView mv = new ModelAndView("estabelecimento/CadastroEstabelecimento");

		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastro(@Valid Estabelecimento estabelecimento,BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estabelecimento);
		}
		
		attributes.addFlashAttribute("mensagem", "Estabelecimento salvo com sucesso!");
		
		return new ModelAndView("redirect:/estabelecimento/novo");
		
	}
}
