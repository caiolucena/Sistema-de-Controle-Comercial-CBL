package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		return mv;
	}
	
	@RequestMapping(value = "/novo",method = RequestMethod.POST)
	public ModelAndView  cadastrar(@Valid Produto produto, BindingResult result,
			RedirectAttributes attributes) { //ela pega o nome l� do form
		if(result.hasErrors()) {
			return novo(produto);
		}
		
		//salvar no banco
		
		//ele cria uma sess�o provis�ria e mostra a msg antes de redirecionar
		//redireciona pra uma url, e n�o para nome da view
		attributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
	}
	
}
