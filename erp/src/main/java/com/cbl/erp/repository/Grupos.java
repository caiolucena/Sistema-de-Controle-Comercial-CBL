package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbl.erp.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo,Integer>{
	
	Optional <Grupo> findByNomeIgnoreCase(String nome);
	
}
