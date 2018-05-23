package com.cbl.erp.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public class Estabelecimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;

	@NotBlank(message = "CNPJ é obrigatório")
	@CNPJ
	private String cnpj;

	@NotBlank(message = "Nome Empresarial é obrigatório")
	private String nome;

	private String nomeFantasia;

	@NotBlank(message=  "Telefone é obrigatório")
	private String telefone;

	private String telefoneOpt;
	
	@Email(message = "E-mail inválido")
	@Size(min =1,message=  "Email é obrigatório")
	private String email;

	
//	@JsonIgnore
//	@Embedded
	@NotNull(message = "Digite um endereço válido")
	private Endereco endereco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Estabelecimento other = (Estabelecimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public String getTelefoneOpt() {
		return telefoneOpt;
	}

	public void setTelefoneOpt(String telefoneOpt) {
		this.telefoneOpt = telefoneOpt;
	}
	

}
