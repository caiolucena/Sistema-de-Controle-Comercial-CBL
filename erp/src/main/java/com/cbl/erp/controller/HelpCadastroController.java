package com.cbl.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbl.erp.model.Estabelecimento;

@Controller
@RequestMapping("/help")
public class HelpCadastroController {
	
	@GetMapping("/estabelecimento/novo")
	public ModelAndView novo(Estabelecimento estabelecimento) {
		
		ModelAndView mv = new ModelAndView ("help/estabelecimento/CadastroEstabelecimento");
		
		return mv;
	}
	
	@GetMapping("/estabelecimento/visualizar")
	public ModelAndView visualizar() {
		ModelAndView mv = new ModelAndView ("help/estabelecimento/VisualizaEstabelecimento");
		return mv;
	}
	
	@GetMapping("/estabelecimento/editar")
	public ModelAndView editar() {
		ModelAndView mv = new ModelAndView ("help/estabelecimento/EditaEstabelecimento");
		return mv;
	}
	
	
}
