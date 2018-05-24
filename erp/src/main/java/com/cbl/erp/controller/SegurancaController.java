package com.cbl.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegurancaController {
	@GetMapping("/index")
	public String paginaInicial() {
		return "index";
	}
	
	
	//NA HORA DE FAZER O LOGIN
	// DÁ UM ESTABELECIMENTOS.FINDONE
	//SE NAO VIER NENHUM
	// VAI PRA TELA DE CADASTRO
	//SE VIER ALGUM VAI PRO INDEX
}
