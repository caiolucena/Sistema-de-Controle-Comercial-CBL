package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.codigos.Icms;

public interface Icmss extends JpaRepository <Icms,Integer>{
	
		Optional<Icms> findByCodigoIgnoreCase(String codigo);
}
