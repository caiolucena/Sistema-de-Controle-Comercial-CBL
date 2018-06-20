package com.cbl.erp.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.cbl.erp.model.ItemVenda;
import com.cbl.erp.model.Produto;

@SessionScope
@Component
public class TabelaItensSession {

	private Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Produto produto, int quantidade) {

		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(produto,quantidade);
		tabelas.add(tabela);
		
		
	}


	public void excluirItem(String uuid, Produto produto) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(produto);
		
		
	}

	public List<ItemVenda> getItens(String uuid) {

		return buscarTabelaPorUuid(uuid).getItens();
	}

	public void alterarQuantidadeItens(String uuid, Produto produto, Integer quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(produto, quantidade);
		
	}
	
	public Object getValorTotal(String uuid) {
 
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}
	
	private TabelaItensVenda  buscarTabelaPorUuid(String uuid) {
		TabelaItensVenda tabela = tabelas.stream()
				.filter(t-> t.getUuid().equals(uuid))
				.findAny().orElse(new TabelaItensVenda(uuid));
		return tabela;
	}


	
}
