package com.cbl.erp.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbl.erp.model.codigos.Icms;
import com.cbl.erp.repository.Icmss;


@Service
public class CrudIcmsService {
	private static Logger logger = Logger.getLogger(CrudIcmsService.class);
	@Autowired
	private Icmss icmss;
	
	@PersistenceContext
    private EntityManager manager;
	


	public Icms buscarPorId(int id) {
		// TODO Auto-generated method stub
		return manager.createQuery("select e from Icms e where e.id = '"+id+"'",Icms.class).getSingleResult();
	}
}