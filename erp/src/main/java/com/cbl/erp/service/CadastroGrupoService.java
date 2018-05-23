package com.cbl.erp.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

/**
 * Essa é a classe de Serviço do Grupo, que contém os métodos responsáveis por realizar buscas desse objeto no banco de dados.
 * @author Caio Lucena
 *
 */
@Service
public class CadastroGrupoService {
	
	@PersistenceContext
	private EntityManager manager;
	
}
