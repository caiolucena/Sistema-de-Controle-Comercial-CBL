package com.cbl.erp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.Grupo;

/**
 * Essa é a classe de Serviço do Grupo, que contém os métodos responsáveis por realizar buscas desse objeto no banco de dados.
 * @author Caio Lucena
 *
 */
@Service
public class CadastroGrupoService {
	
	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * ESse método é responsável por realizar uma busca de grupos que não contenham administrador na tabela Grupo do banco de dados
	 * @return List<Grupo> contendo os objetos referentes à busca
	 */
	@Transactional
	public List<Grupo> buscaGrupos() {
		return manager.createQuery("select a from Grupo a where a.nome !='administrador'",Grupo.class).getResultList();
	}
}
