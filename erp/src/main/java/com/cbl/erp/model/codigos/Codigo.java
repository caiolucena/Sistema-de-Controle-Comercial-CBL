package com.cbl.erp.model.codigos;

import org.hibernate.validator.constraints.NotBlank;



public class Codigo {

	private int id;
	
	@NotBlank(message= " O código é obrigatório")
	private String codigo;
	
	@NotBlank(message= " A descrição é obrigatória")
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Codigo other = (Codigo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
