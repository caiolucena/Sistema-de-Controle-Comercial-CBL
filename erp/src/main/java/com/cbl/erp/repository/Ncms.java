package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.codigos.Ncm;

public interface Ncms extends JpaRepository <Ncm,Integer>{
	
		Optional<Ncm> findByCodigoIgnoreCase(String codigo);
}
