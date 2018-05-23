package com.cbl.erp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Endereco implements Serializable {		

	private static final long serialVersionUID = 1L;

	@Size(min =1,max = 35, message = "Logradouro é obrigatório, tamanho entre 1 e 35 caracteres")
	private String logradouro;
	
	@NotBlank(message = "Número é Obrigatório, Se não houver, digite SN")
	private String numero;
	
	private String complemento;
	
	@NotBlank(message = "CEP é Obrigatório")
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cidade")
	@NotNull(message=" Cidade é obrigatória")
	private Cidade cidade;
	
	@Transient
	private Estado estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
