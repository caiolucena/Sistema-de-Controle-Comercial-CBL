package com.cbl.erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Cliente;
import com.cbl.erp.repository.Cidades;
import com.cbl.erp.repository.Clientes;
import com.cbl.erp.repository.Estados;
import com.cbl.erp.service.CrudClienteService;
import com.cbl.erp.service.exception.ItemDuplicadoException;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	Cidades cidades;

	@Autowired
	Estados estados;

	@Autowired
	CrudClienteService crudClienteService;

	@Autowired
	Clientes clientes;

	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("cidades", cidades.findAll());
		mv.addObject("estados", estados.findAll());
		return mv;

	}

	@PostMapping("/novo")
	public ModelAndView cadastro(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		try {
			crudClienteService.salvar(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		} catch (ItemDuplicadoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
		}

		return new ModelAndView("redirect:/clientes/novo");

	}

	@RequestMapping(path = "/busca", method = RequestMethod.GET)
	public ModelAndView retornarTodos() {
		ModelAndView mv = new ModelAndView("cliente/ProcurarCliente");
		mv.addObject("clientes", clientes.findAll());
		return mv;
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return clientes.findByNomeStartingWithIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
}


