 package com.cbl.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.Venda;
import com.cbl.erp.repository.helper.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Integer>, VendasQueries {

	
}
