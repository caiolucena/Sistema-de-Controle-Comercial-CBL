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
	
	String uuid;
	
	
	
	public TabelaItensVenda(String uuid) {
		this.uuid = uuid;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	
	
}
