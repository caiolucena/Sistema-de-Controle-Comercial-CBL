package com.cbl.erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.dto.ProdutoDTO;
import com.cbl.erp.model.Cliente;
import com.cbl.erp.model.Produto;
import com.cbl.erp.repository.Cfops;
import com.cbl.erp.repository.Icmss;
import com.cbl.erp.repository.Ncms;
import com.cbl.erp.repository.Produtos;
import com.cbl.erp.service.CrudProdutoService;
import com.cbl.erp.service.exception.ImpossivelExcluirEntidadeException;
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

	@Autowired
	Produtos produtos;

	@GetMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("ncms", ncms.findAll());
		mv.addObject("cfops", cfops.findAll());
		mv.addObject("icmss", icmss.findAll());
		// mv.addObject("cests", cests.findAll());
		return mv;
	}

	@RequestMapping(value = { "novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(produto);
		}
		String mensagem;
		if (produto.isNovo()) {
			mensagem = "Produto salvo com sucesso!";
		} else {
			mensagem = "Produto atualizado com sucesso!";
		}
		try {
			
			crudprodutoService.salvar(produto);
		} catch (ItemDuplicadoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return (novo(produto));
		}

		attributes.addFlashAttribute("mensagem", mensagem);
		return new ModelAndView("redirect:/produtos/novo");
	}

	@RequestMapping(path = "/busca", method = RequestMethod.GET)
	public ModelAndView retornarTodos() {
		ModelAndView mv = new ModelAndView("produto/ProcurarProduto");
		mv.addObject("produtos", produtos.findAll());
		return mv;
	}

	// @RequestMapping(value= "/busca",method = RequestMethod.GET, consumes = {
	// MediaType.APPLICATION_JSON_VALUE })
	// public List<Produto> retornarTodos(){
	//
	// List<Produto> retorno = produtos.findAll();
	//
	// return retorno;
	// }
	//
	//
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable int id) {
		try {
			Produto produto = produtos.findOne(id);
			crudprodutoService.excluir(produto);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProdutoDTO> pesquisar(String nome) {
		return produtos.porNome(nome);

	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable int id) {
		Produto produto = produtos.findOne(id);
		ModelAndView mv = novo(produto);
		mv.addObject(produto);
		return mv;
	}
}
