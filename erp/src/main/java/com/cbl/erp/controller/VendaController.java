package com.cbl.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cbl.erp.model.Produto;
import com.cbl.erp.repository.Produtos;
import com.cbl.erp.repository.Vendas;
import com.cbl.erp.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	Vendas vendas;

	@Autowired
	Produtos produtos;

	@Autowired
	TabelaItensVenda tabelaItensVenda;

	@GetMapping("/nova")
	public String nova() {
		return "venda/CadastroVenda";
	}

	@RequestMapping(path = "/busca", method = RequestMethod.GET)
	public ModelAndView retornarTodos() {
		ModelAndView mv = new ModelAndView("venda/ProcurarVenda");

		mv.addObject("vendas", vendas.findAll());
		return mv;
	}

	@PostMapping("/item")
	public ModelAndView adicionarItem(int idProduto) {

		Produto produto = produtos.findOne(idProduto);
		tabelaItensVenda.adicionarItem(produto, 1);
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");

		mv.addObject("itens", tabelaItensVenda.getItens());
		mv.addObject("valorTotal", tabelaItensVenda.getValorTotal());
		return mv;
	}

	@RequestMapping(value = "/item/{idProduto}", method = RequestMethod.POST)
	public ModelAndView alterarQuantidadeItem(@PathVariable int idProduto, String quantidade) {
		Produto produto = produtos.findOne(idProduto);

		tabelaItensVenda.adicionarItem(produto, Integer.parseInt(quantidade));
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		mv.addObject("valorTotal", tabelaItensVenda.getValorTotal());
		return mv;
	}

	@DeleteMapping("/item/{idProduto}")
	public ModelAndView excluirItem(@PathVariable int idProduto) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		Produto produto = produtos.findOne(idProduto);
		tabelaItensVenda.excluirItem(produto);
		mv.addObject("itens", tabelaItensVenda.getItens());
		mv.addObject("valorTotal", tabelaItensVenda.getValorTotal());
		return mv;
	}

}
