package com.cbl.erp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Produto {

	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@Size(min = 1, max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
	private String descricao;

	@NotNull(message = "Código NCM é obrigatório")
	private int ncm;
	
	@NotNull(message = "Código CEST é obrigatório")
	private int cest;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNcm() {
		return ncm;
	}

	public void setNcm(int ncm) {
		this.ncm = ncm;
	}

	public int getCest() {
		return cest;
	}

	public void setCest(int cest) {
		this.cest = cest;
	}
	
	
}
