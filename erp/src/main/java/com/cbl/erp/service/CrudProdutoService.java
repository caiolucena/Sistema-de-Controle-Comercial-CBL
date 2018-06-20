package com.cbl.erp.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.Produto;
import com.cbl.erp.repository.Produtos;
import com.cbl.erp.service.exception.ImpossivelExcluirEntidadeException;
import com.cbl.erp.service.exception.ItemDuplicadoException;

@Service
public class CrudProdutoService {


	@Autowired
	Produtos produtos;
	
	@Transactional
	public Produto salvar (Produto produto) {
		
		Optional<Produto> produtoOptional = produtos.findByNomeIgnoreCase(produto.getNome());
		if(produtoOptional.isPresent()){
			ItemDuplicadoException e = new ItemDuplicadoException(" Produto já Cadastrado!");
			
			throw e;
		}
		try {
			return produtos.save(produto);
			
		} catch (Exception e) {
			
			return null;
		}

	}
	
	@Transactional
	public void excluir(Produto produto) {
		try {
			produtos.delete(produto);
			produtos.flush();
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar produto. Já foi usado em alguma venda.");
		}
	}

	
}
