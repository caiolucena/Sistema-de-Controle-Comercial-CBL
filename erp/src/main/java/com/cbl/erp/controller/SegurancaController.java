package com.cbl.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cbl.erp.repository.Clientes;
import com.cbl.erp.repository.Produtos;
import com.cbl.erp.repository.Vendas;
import com.cbl.erp.service.CrudProdutoService;

/**
 * Essa é a classe Controller da classe Seguranca, e é responsável por fazer a ponte entre as views referentes a esse objeto e os Models, de acordo com as solicitações realizadas nas rotas.
 * @author Caio Lucena
 *
 */
@Controller
public class SegurancaController {
	
	@Autowired
	Clientes clientes;
	
	@Autowired
	Produtos produtos;
	
	@Autowired
	Vendas vendas;
	
	@Autowired
	CrudProdutoService crudProdutoService;
	/**
	 * Esse é o método responsável pela autenticação do login do usuário.
	 * @param user, que são os dados do login do usuário
	 * @return "redirect:/tccs/novo", que redireciona o usuário para a página da biblioteca em caso do login estar correto
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@AuthenticationPrincipal User user){
		if(user != null) {
			return "redirect:/index";
		}
		return "Login";
	}
	

	/**
	 * Esse método é responsável por retornar um erro caso o login do usuário esteja incorreto.
	 * @return 403, renderiza a página de erro 403
	 */
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
	

	/**
	 * Esse método é responsável por renderizar a pagina inicial do sistema
	 * @return index, que é a pagina inicial da biblioteca
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView paginaInicial() {
		ModelAndView mv = new ModelAndView("index");
		
		mv.addObject("qntClientes",clientes.count());
		mv.addObject("qntItens", produtos.count());
		mv.addObject("vendasNoAno", vendas.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendas.valorTotalNoMes());
		mv.addObject("valorEstoque",produtos.valorItensEstoque().getValor());
		return mv;
	}
	
}
