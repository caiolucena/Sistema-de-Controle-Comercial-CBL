package com.cbl.erp.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbl.erp.model.Cidade;
import com.cbl.erp.repository.Cidades;
import com.cbl.erp.service.exception.ItemDuplicadoException;


@Service
public class CadastroCidadeService {
	private static Logger logger = Logger.getLogger(CadastroCidadeService.class);
	@Autowired
	private Cidades cidades;
	
	@PersistenceContext
    private EntityManager manager;
	
	/**
	 * Esse é o método responsável por realizar buscas por UF no banco de dados
	 * @param uf, que é a String contendo a UF 
	 * @return List<Cidade> contendo o(s) objeto(s) referente(s) a busca
	 */
	@Transactional
	public List<Cidade> buscarPorUf(String uf){
		return manager.createQuery("select e from Cidade e where e.uf = '"+uf+"'",Cidade.class).getResultList();
	}
	
	/**
	 * Esse é o método responsável por fazer uma busca por nome no banco de dados
	 * @param busca, que é a String que contém o parâmetro de busca por Cidade no banco de dados
	 * @return List<Cidade> contendo o(s) objeto(s) referente(s) à busca
	 */
	@Transactional
	public Cidade buscarPorNome(String nome){
		return manager.createQuery("select e from Cidade e where e.nome = '"+nome+"'",Cidade.class).getSingleResult();
	}
	
	/**
	 * Esse é o método responsável por salvar um objeto no banco de dados
	 * @param cidade, que é o objeto que irá ser salvo no banco de dados.
	 */
	@Transactional
	public Cidade salvar (Cidade cidade) {
		
		Optional <Cidade> cidadeOptional = cidades.findByNomeIgnoreCase(cidade.getNome());
		if(cidadeOptional.isPresent()){
			throw new ItemDuplicadoException(" Cidade já Cadastrada!");
		}
		try {
			return cidades.saveAndFlush(cidade);
		} catch (Exception e) {
			logger.error("Erro ao cadastrar cidade!",e);			
		}
		return null;
	}
	
	/**
	 * Esse é o método responsável por remover um objeto no banco de dados
	 * @param id, que é o id do objeto que irá ser removido da tabela de Cidade no banco de dados.
	 * @return true or false, dependendo do sucesso ou falha da remoção
	 */
	@Transactional
	public boolean remover(int id) {
		if(id>0){
			try {
				cidades.delete(id);
				logger.info("Cidade removida com sucesso.");
				return true;
			} catch (Exception e) {
				logger.info(" Erro ao remover a Cidade.");				
			}
		}
		return false;
	}

}