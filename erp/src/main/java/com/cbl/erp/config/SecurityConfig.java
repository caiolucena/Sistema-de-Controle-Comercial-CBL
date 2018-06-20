package com.cbl.erp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cbl.erp.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("CADASTRO_ACERVO");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/usuarios/novo").hasAuthority("CADASTRAR_USUARIO")
			.antMatchers("/usuarios/deletar").hasAuthority("DELETAR_USUARIO")
			.antMatchers("/usuarios/busca").hasAuthority("BUSCAR_USUARIO")
			
			.antMatchers("/clientes/novo").hasAuthority("CADASTRAR_CLIENTE")
			.antMatchers("/clientes/deletar").hasAuthority("DELETAR_CLIENTE")
			
			.antMatchers("/produtos/novo").hasAuthority("CADASTRAR_PRODUTO")
			.antMatchers("/produtos/editar").hasAuthority("EDITAR_PRODUTO")
			.antMatchers("/produtos/deletar").hasAuthority("DELETAR_PRODUTO")
			
			.antMatchers("/vendas/novo").hasAuthority("CADASTRAR_VENDA")
			.antMatchers("/vendas/deletar").hasAuthority("DELETAR_VENDA")
			
			
			.antMatchers("/estabelecimento/editar").hasAuthority("EDITAR_ESTABELECIMENTO")
			.antMatchers("/estabelecimento/novo").hasAuthority("CADASTRAR_ESTABELECIMENTO")
			.antMatchers("/estabelecimento/visualizar").hasAuthority("VISUALIZAR_ESTABELECIMENTO")
			.anyRequest().authenticated()
			
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.exceptionHandling().accessDeniedPage("/403")
			.and()
			.csrf().disable(); 
		
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/layout/**")
		.antMatchers("/layout/fonts/**")
		.antMatchers("/images/**")
		.antMatchers("/stylesheets/**");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
