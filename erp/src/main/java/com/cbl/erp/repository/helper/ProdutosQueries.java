package com.cbl.erp.repository.helper;

import java.util.List;

import com.cbl.erp.dto.ProdutoDTO;
import com.cbl.erp.dto.ValorItensEstoque;

public interface ProdutosQueries {

	public List <ProdutoDTO> porNome(String nome);
	public ValorItensEstoque valorItensEstoque();
}
