package com.cbl.erp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbl.erp.model.codigos.Cfop;
import com.cbl.erp.repository.Cfops;


@Service
public class CrudCfopService {
	private static Logger logger = Logger.getLogger(CrudCfopService.class);
	@Autowired
	private Cfops cfops;
	
	@PersistenceContext
    private EntityManager manager;
	


	public Cfop buscarPorId(int id) {
		// TODO Auto-generated method stub
		return manager.createQuery("select e from Cfop e where e.id = '"+id+"'",Cfop.class).getSingleResult();
	}



	public List<Cfop> buscarPorIcms(int id) {
		return manager.createQuery("select e from Cfop e where e.icms.id= '"+id+"'",Cfop.class).getResultList();
	}
}