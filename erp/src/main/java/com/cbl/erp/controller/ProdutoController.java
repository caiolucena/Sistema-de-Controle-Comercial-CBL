package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Produto;
import com.cbl.erp.repository.Cfops;
import com.cbl.erp.repository.Icmss;
import com.cbl.erp.repository.Ncms;
import com.cbl.erp.service.CrudProdutoService;
import com.cbl.erp.service.exception.ItemDuplicadoException;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	Ncms ncms;
	@Autowired
	Icmss icmss;
	@Autowired
	Cfops cfops;
	
	@Autowired
	CrudProdutoService crudprodutoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("ncms", ncms.findAll());
		mv.addObject("cfops", cfops.findAll());
		mv.addObject("icmss", icmss.findAll());
		//mv.addObject("cests", cests.findAll());
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
		try {
			System.out.println(produto.getCfop().getId());
			System.out.println(produto.getNcm().getId());
			System.out.println(produto.getCfop().getIcms().getId());
			crudprodutoService.salvar(produto);
		} catch (ItemDuplicadoException e) {
			result.rejectValue("titulo", e.getMessage(), e.getMessage());
			return(novo(produto));
		}
		
		attributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
	}
	
}
