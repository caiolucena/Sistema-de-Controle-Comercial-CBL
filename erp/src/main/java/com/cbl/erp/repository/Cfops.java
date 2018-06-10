package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.codigos.Cfop;

public interface Cfops extends JpaRepository <Cfop,Integer>{
	
		Optional<Cfops> findByCodigoIgnoreCase(String codigo);
}
