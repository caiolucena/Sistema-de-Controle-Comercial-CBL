package com.cbl.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbl.erp.model.Estabelecimento;

@Controller
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

	
	@RequestMapping("/novo")
	public ModelAndView novo(Estabelecimento estabelecimento) {
		
		ModelAndView mv = new ModelAndView("estabelecimento/CadastroEstabelecimento");
		
		return mv;
	}
}
