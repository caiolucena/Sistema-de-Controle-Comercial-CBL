package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Produto;

@Controller
public class ProdutoController {

	
	@RequestMapping("/produto/novo")
	public String novo(Produto produto) {
		return "produto/cadastroProduto";
	}
	
	@RequestMapping(value = "/produto/novo",method = RequestMethod.POST)
	public String  cadastrar(@Valid Produto produto, BindingResult result, Model model,
			RedirectAttributes attributes) { //ela pega o nome lá do form
		if(result.hasErrors()) {
			return novo(produto);
		}
		
		//salvar no banco
		
		//ele cria uma sessão provisária e mostra a msg antes de redirecionar
		//redireciona pra uma url, e não para nome da view
		attributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		return "redirect:/produto/novo";
	}
	
}
