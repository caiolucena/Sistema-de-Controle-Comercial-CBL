package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.Produto;


public interface Produtos extends JpaRepository<Produto,Integer> {

	Optional<Produto> findByNomeIgnoreCase(String nome);
	
}
