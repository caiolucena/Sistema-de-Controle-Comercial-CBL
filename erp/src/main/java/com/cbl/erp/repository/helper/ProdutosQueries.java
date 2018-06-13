package com.cbl.erp.repository.helper;

import com.cbl.erp.dto.ProdutoDTO;

import java.util.List;

public interface ProdutosQueries {

	public List <ProdutoDTO> porNome(String nome);
	
}
