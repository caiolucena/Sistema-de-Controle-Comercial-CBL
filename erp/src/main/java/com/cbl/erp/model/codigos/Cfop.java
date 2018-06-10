package com.cbl.erp.model.codigos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cfop")
public class Cfop {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(value =1,message = " Código CFOP é obrigatório")
	private int id;

	String codigo;
	
	String descricao;
	
	@Transient
	String icmsDescricao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "icms_id")
	@JsonIgnore
	private Icms icms;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Icms getIcms() {
		return icms;
	}

	public void setIcms(Icms icms) {
		this.icms = icms;
	}
	
	public String getIcmsDescricao() {
		return icmsDescricao;
	}

	public void setIcmsDescricao(String icmsDescricao) {
		this.icmsDescricao = icmsDescricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cfop other = (Cfop) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
