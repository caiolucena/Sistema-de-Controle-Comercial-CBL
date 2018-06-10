package com.cbl.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbl.erp.model.codigos.Icms;
import com.cbl.erp.service.CrudIcmsService;

@Controller
@RequestMapping("/codigos/icms")
public class IcmsController {
	@Autowired
	private CrudIcmsService icmsService;
	
	/**
	 * Esse método é responsável por fazer a busca das cidades cadastradas no banco, pelo parâmetro UF
	 * @param estado, que é o estado referente às cidades que se deseja fazer a busca
	 * @return ResponseEntity.ok(retorno), que é a confirmação da busca
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> buscar(@RequestBody Icms icms){
		
		Icms retorno = icmsService.buscarPorId(icms.getId());
		return ResponseEntity.ok(retorno);	
	} 
	
}
