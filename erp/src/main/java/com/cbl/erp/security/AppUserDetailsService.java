package com.cbl.erp.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbl.erp.model.Usuario;
import com.cbl.erp.repository.Usuarios;
import com.cbl.erp.service.CadastroUsuarioService;

/**
 * Essa é a classe responsável por conter os serviços relacionados ao login e cadastro dos usuários do sistema.
 * @author EquipeACL
 *
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	

	/**
	 * Esse método é responsável pelo direcionamento e validação na autenticação do usuário (admin ou funcionário) e do aluno no login.
	 * @param login, que é a String que contém os dados de login do usuário
	 * @return new User com as permissões do usuário que realizou o login
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarios.findByLoginIgnoreCase(login);

			Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
			return new UsuarioSistema(usuario, getPermissoes(usuario));
		

	}

	/**
	 * Esse método é responsável por atribuir permissões de acesso de rotas a um Funcionário
	 * @param aluno, que é o aluno que irá receber as permissões
	 * @return authorities, que são as autorizações do funcionário (rotas que podem ser acessadas)
	 */
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		// lista de permissões do Funcionario
		List <String> permissoes = cadastroUsuarioService.permissoes(usuario);
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		
		return authorities;
	}

}
