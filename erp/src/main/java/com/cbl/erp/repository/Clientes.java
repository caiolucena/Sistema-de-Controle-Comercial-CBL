package com.cbl.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.Cliente;

public interface Clientes extends JpaRepository <Cliente,Integer> {

	Optional<Cliente> findByCpf(String cpf);
}
