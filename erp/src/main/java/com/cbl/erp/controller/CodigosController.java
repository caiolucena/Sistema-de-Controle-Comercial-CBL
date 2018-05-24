package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.codigos.Codigo;

@Controller
@RequestMapping("/codigos")
public class CodigosController {

	
	@GetMapping("/novo")
	public ModelAndView novo(Codigo codigo) {
		
		ModelAndView mv = new ModelAndView("codigo/CadastroCodigo");
		
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Codigo codigo,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(codigo);
		}
		
		return new ModelAndView("redirect:/codigos/novo");
		
	}
}
