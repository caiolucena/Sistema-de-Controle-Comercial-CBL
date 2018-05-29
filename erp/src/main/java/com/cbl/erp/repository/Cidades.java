package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.Cidade;

public interface Cidades extends JpaRepository <Cidade,Integer>{
	
		Optional<Cidade> findByNomeIgnoreCase(String nome);
}
