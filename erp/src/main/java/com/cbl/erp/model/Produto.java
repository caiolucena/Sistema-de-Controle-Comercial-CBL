package com.cbl.erp.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.cbl.erp.model.codigos.Cfop;
import com.cbl.erp.model.codigos.Ncm;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = " A descrição do produto é obrigatória")
	private String nome;
	
	@Size(max = 60, message = "As informações adicionais devem conter no máximo 60 caracteres")
	private String descricao;

	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ncm_id")
	@JsonIgnore
	private Ncm ncm;

	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cfop_id")
	@JsonIgnore
	private Cfop cfop;
	
	@NotNull(message = " Informe a quantidade de produtos no seu estoque")
	private Integer estoque;
	
	@NotNull(message = " Informe o preço do produto")
	@DecimalMin(value = "0.01",message = "O valor da cerveja deve ser maior do que R$ 0,01")
	@DecimalMax(value = "99999.99",message = "O valor da cerveja deve ser menor do que R$ 99.999,99")
	private BigDecimal preco;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	
	
	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}