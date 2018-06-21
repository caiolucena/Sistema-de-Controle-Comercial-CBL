package com.cbl.erp.repository.helper.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cbl.erp.dto.ProdutoDTO;
import com.cbl.erp.dto.ValorItensEstoque;
import com.cbl.erp.repository.helper.ProdutosQueries;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ProdutoDTO> porNome(String nome) {
		
		String jpql = "select new com.cbl.erp.dto.ProdutoDTO(id,nome,preco) "
						+ "from Produto where lower(nome) like lower(:nome)";
		List<ProdutoDTO> produtosFiltrados = manager.createQuery(jpql, ProdutoDTO.class)
				.setParameter("nome","%"+nome +"%")
				.getResultList();
		return produtosFiltrados;
	}
	
	
	
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new com.cbl.erp.dto.ValorItensEstoque(sum(preco* estoque), sum(estoque)) from Produto";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
	}
}
