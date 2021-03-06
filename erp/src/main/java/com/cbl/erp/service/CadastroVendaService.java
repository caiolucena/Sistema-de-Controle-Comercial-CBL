package com.cbl.erp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.ItemVenda;
import com.cbl.erp.model.Venda;
import com.cbl.erp.model.Venda;
import com.cbl.erp.repository.Vendas;
import com.cbl.erp.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroVendaService {

	
	@Autowired 
	private Vendas vendas;
	
	@Transactional
	public void salvar(Venda venda) {
		
		if (venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		}
			
		BigDecimal valorTotalItens = venda.getItens().stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.get();
		BigDecimal valorTotal = calcularValorTotal(valorTotalItens, venda.getValorFrete(), venda.getValorDesconto());
		venda.setValorTotal(valorTotal);
		
		if (venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega(), venda.getHorarioEntrega()));
		}
		
		vendas.save(venda);
	}
	
	private BigDecimal calcularValorTotal(BigDecimal valorTotalItens, BigDecimal valorFrete, BigDecimal valorDesconto) {
		BigDecimal valorTotal = valorTotalItens
				.add(Optional.ofNullable(valorFrete).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(valorDesconto).orElse(BigDecimal.ZERO));
		return valorTotal;
	}
	@Transactional
	public void excluir(Venda venda) {
		try {
			vendas.delete(venda);
			vendas.flush();
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar venda");
		}
	}
}
