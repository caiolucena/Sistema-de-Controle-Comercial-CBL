package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbl.erp.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository <Usuario,Integer> {
	
	Optional <Usuario> findByNomeIgnoreCase(String nome);
	Optional <Usuario> findByCpfIgnoreCase(String cpf);
	Optional <Usuario> findByLoginIgnoreCase(String login);
}
