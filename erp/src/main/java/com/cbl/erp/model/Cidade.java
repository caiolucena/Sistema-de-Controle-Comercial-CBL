package com.cbl.erp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cidade")
public class Cidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(value =1,message = " Selecione uma Cidade")
	private int id;
	

	private String nome;
	
	@NotNull(message = "Estado é obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_id")
	@JsonIgnore
	private Estado estado;
	
	/**
	 * M�todo construtor da classe Cidade
	 * Construtor vazio (utilizado para criar um objeto do tipo Cidade sem par�metros definidos)
	 */
	public Cidade() {
		
	}
	
	/**
	 * M�todo construtor da classe Cidade (utilizado para criar um objeto do tipo Cidade com par�metros definidos)
	 * @param id, id da cidade
	 * @param codigo, codigo da cidade
	 * @param nome, nome da cidade
	 * @param uf, uni�o federativa da cidade
	 */

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		Cidade other = (Cidade) obj;
		if (id != other.getId())
			return false;
		return true;
	}
	
	

}
