package com.cbl.erp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Venda;
import com.cbl.erp.model.Produto;
import com.cbl.erp.model.Venda;
import com.cbl.erp.repository.Produtos;
import com.cbl.erp.repository.Vendas;
import com.cbl.erp.security.UsuarioSistema;
import com.cbl.erp.service.CadastroVendaService;
import com.cbl.erp.service.exception.ImpossivelExcluirEntidadeException;
import com.cbl.erp.session.TabelaItensSession;

@Controller
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	Vendas vendas;

	@Autowired
	CadastroVendaService cadastroVendaService;

	@Autowired
	Produtos produtos;

	@Autowired
	TabelaItensSession tabelaItens;

	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		venda.setUuid(UUID.randomUUID().toString());
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (result.hasErrors()) {
			return nova(venda);
		}
		venda.setUsuario(usuarioSistema.getUsuario());
		venda.adicionarItens(tabelaItens.getItens(venda.getUuid()));

		cadastroVendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso");

		return new ModelAndView("redirect:/vendas/nova");
	}

	@RequestMapping(path = "/busca", method = RequestMethod.GET)
	public ModelAndView retornarTodos() {
		ModelAndView mv = new ModelAndView("venda/ProcurarVenda");
		mv.addObject("vendas", vendas.findAll());
		return mv;
	}

	@PostMapping("/item")
	public ModelAndView adicionarItem(int idProduto, String uuid) {

		Produto produto = produtos.findOne(idProduto);
		tabelaItens.adicionarItem(uuid, produto, 1);

		//
		return mvTabelaItensVenda(uuid);
	}

	@RequestMapping(value = "/item/{idProduto}", method = RequestMethod.POST)
	public ModelAndView alterarQuantidadeItem(@PathVariable int idProduto, String quantidade, String uuid) {
		Produto produto = produtos.findOne(idProduto);

		tabelaItens.alterarQuantidadeItens(uuid, produto, Integer.parseInt(quantidade));

		// mv.addObject("valorTotal", tabelaItens.getValorTotal());
		return mvTabelaItensVenda(uuid);
	}

	@DeleteMapping("/item/{uuid}/{idProduto}")
	public ModelAndView excluirItem(@PathVariable int idProduto, @PathVariable String uuid) {

		Produto produto = produtos.findOne(idProduto);
		tabelaItens.excluirItem(uuid, produto);

		// mv.addObject("valorTotal", tabelaItens.getValorTotal());
		return mvTabelaItensVenda(uuid);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable int id) {
		try {
			Venda venda = vendas.findOne(id);
			cadastroVendaService.excluir(venda);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable int id) {
		Venda venda = vendas.findOne(id);
		ModelAndView mv= nova(venda);
		mv.addObject(venda);
		return mv;
	}
	
	
	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		return mv;
	}

	
	
}
