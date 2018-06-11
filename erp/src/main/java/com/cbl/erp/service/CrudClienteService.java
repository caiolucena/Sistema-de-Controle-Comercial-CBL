package com.cbl.erp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.Cliente;
import com.cbl.erp.repository.Clientes;
import com.cbl.erp.service.exception.ItemDuplicadoException;

@Service
public class CrudClienteService {

	@Autowired
	Clientes clientes;
	
	@Transactional
	public Cliente salvar (Cliente cliente) {
		
		
		Optional <Cliente> alunoOptional = clientes.findByCpf(cliente.getCpf());
		
		if(alunoOptional.isPresent()){
			throw new ItemDuplicadoException(" Cliente já Cadastrado!");
		}
		try {
			return clientes.saveAndFlush(cliente);
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
