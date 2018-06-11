package com.cbl.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cbl.erp.repository.Vendas;

@Controller
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	Vendas vendas;
	
	@RequestMapping(path="/busca", method=RequestMethod.GET)
	public ModelAndView retornarTodos(){
		ModelAndView mv = new ModelAndView("venda/ProcurarVenda");
		
		mv.addObject("vendas", vendas.findAll());
		System.out.println(vendas.findAll().get(0).getId());
		return mv;
	}
	
}
