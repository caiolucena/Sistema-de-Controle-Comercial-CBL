//package com.cbl.erp.model.codigos;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.Valid;
//
//import org.hibernate.validator.constraints.NotBlank;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
////@Entity
////@Table(name = "cest")
//public class Cest extends Codigo {
//
//	@NotBlank(message = " Código CEST é obrigatório")
//	String codigo;
//	
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ncm_id")
//	@JsonIgnore
//	private Ncm ncm;
//
//
//	public String getCodigo() {
//		return codigo;
//	}
//
//
//	public void setCodigo(String codigo) {
//		this.codigo = codigo;
//	}
//
//
//	public Ncm getNcm() {
//		return ncm;
//	}
//
//
//	public void setNcm(Ncm ncm) {
//		this.ncm = ncm;
//	}
//	
//	
//}
