package com.cbl.erp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbl.erp.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {


	public List<Cliente> findByNomeStartingWithIgnoreCase(String nome);

	public Optional<Cliente> findByCpf(String cpf);

}
