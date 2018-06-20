package com.cbl.erp.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.Cliente;
import com.cbl.erp.model.Cliente;
import com.cbl.erp.repository.Clientes;
import com.cbl.erp.service.exception.ImpossivelExcluirEntidadeException;
import com.cbl.erp.service.exception.ItemDuplicadoException;

@Service
public class CrudClienteService {

	@Autowired
	Clientes clientes;

	@Transactional
	public Cliente salvar(Cliente cliente) {

		Optional<Cliente> clienteOptional = clientes.findByCpf(cliente.getCpf());

		if (clienteOptional .isPresent() && !clienteOptional .get().equals(cliente)) {
			throw new ItemDuplicadoException(" Cliente já Cadastrado!");
		}
		try {
			return clientes.saveAndFlush(cliente);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			clientes.delete(cliente);
			clientes.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já foi usado em alguma venda.");
		}
	}
}
