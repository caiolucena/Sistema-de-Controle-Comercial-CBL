package com.cbl.erp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.cbl.erp.model.Usuario;

public class UsuarioSistema extends User {

	private Usuario usuario;
	
	public UsuarioSistema(Usuario usuario, Collection <? extends GrantedAuthority> authorities) {
		super(usuario.getLogin(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	private static final long serialVersionUID = 1L;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
