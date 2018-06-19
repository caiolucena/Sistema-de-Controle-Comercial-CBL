package com.cbl.erp.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.cbl.erp.model.ItemVenda;
import com.cbl.erp.model.Produto;

@SessionScope
@Component
public class TabelaItensVenda {

	private List<ItemVenda> itens = new ArrayList<>();

	public BigDecimal getValorTotal() {
		return itens.stream().map(ItemVenda::getValorTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemVenda> itemVendaOptional = buscarItemPorProduto(produto);

		ItemVenda itemVenda = new ItemVenda();
		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(quantidade);
		} else {
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(produto.getPreco());
			itens.add(0, itemVenda);
		}
	}

	public void excluirItem(Produto produto) {
		int indice = IntStream.range(0,itens.size())
				.filter(i -> itens.get(i).getProduto().equals(produto))
				.findAny().getAsInt();
		itens.remove(indice);
		
	}

	public void alterarQuantidadeItens(Produto produto, Integer quantidade) {
		ItemVenda itemVenda = buscarItemPorProduto(produto).get();
		itemVenda.setQuantidade(quantidade);
	}

	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	private Optional<ItemVenda> buscarItemPorProduto(Produto produto) {
		return itens.stream().filter(i -> i.getProduto().equals(produto)).findAny();
	}

}
