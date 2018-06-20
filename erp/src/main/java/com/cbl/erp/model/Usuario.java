package com.cbl.erp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import com.cbl.validation.AtributoConfirmacao;


/**
 * Essa classe � utilizada como modelo para um objeto do tipo Usuario;
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * Essa classe � a super classe que os usuarios do sistema herdam seus m�todos e atributos, que s�o comuns a todos.
 * @author EquipeACL
 */

@Entity
@Table(name = "usuario")
@AtributoConfirmacao(atributo = "senha", atributoConfirmacao = "confirmacaoSenha",message = "Senhas não conferem")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@CPF
	@NotBlank(message = " CPF é obrigatório")
	protected String cpf;
	
	@NotBlank(message = " O nome é obrigatório")
	protected String nome;

	
	@Size(min = 3,message = " A senha deve possuir no mínimo 3 dígitos")
	protected String senha;
	
	@Transient
	@Size(min = 3, message = " Confirmação de senha deve possuir no mínimo 3 dígitos")
	protected String confirmacaoSenha;
	
	
	@NotBlank(message = " Nome de usuário é obrigatório")
	protected String login;
	
	
	@Fetch(FetchMode.SELECT)
	@Size(min=1,message = "Selecione pelo menos um grupo")
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "usuario_has_grupo",joinColumns = @JoinColumn(name = "usuario_id")
												, inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List <Grupo> grupos;
	
	/**
	 * M�todo Construtor da classe Usu�rio
	 * Construtor vazio (utilizado para criar um objeto do tipo Usuario sem par�metros definidos)
	 */
	public Usuario() { 
		
	}
	
	/**
	 * M�todo Construtor da classe Usu�rio
	 * @param cpf, n�mero do cpf do Usu�rio
	 * @param nome, nome completo do Usu�rio
	 * @param rg, numero do rg do Usu�rio
	 * @param naturalidade, cidade natal do Usu�rio
	 * @param endereco, endere�o completo do Usu�rio
	 * @param telefone, telefone de contato do Usu�rio
	 * @param email, endere�o de email do Usu�rio
	 * @param senha, senha de acesso ao sistema do Usu�rio
	 */
	public Usuario(String cpf, String nome, String telefone,
			String email, String senha, String senhaConfirmacao,String login) {
		setCpf(cpf);
		setNome(nome);
		setSenha(senha);
		setConfirmacaoSenha(senhaConfirmacao);
		setLogin(login);
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean isNovo() {
		return id ==0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
