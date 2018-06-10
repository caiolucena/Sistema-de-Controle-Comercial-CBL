package com.cbl.erp.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbl.erp.model.codigos.Ncm;
import com.cbl.erp.repository.Ncms;


@Service
public class CrudNcmService {
	private static Logger logger = Logger.getLogger(CrudNcmService.class);
	@Autowired
	private Ncms ncms;
	
	@PersistenceContext
    private EntityManager manager;
	


	public Ncm buscarPorId(int id) {
		// TODO Auto-generated method stub
		return manager.createQuery("select e from Ncm e where e.id = '"+id+"'",Ncm.class).getSingleResult();
	}
}