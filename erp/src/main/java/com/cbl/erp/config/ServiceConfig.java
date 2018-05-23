package com.cbl.erp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cbl.erp.service.CadastroGrupoService;
/**
 * Essa a classe de configuração dos serviços, que serve para indicar para o Spring onde está as classes de serviços, 
 * utilizando a classe CadastroGrupoService como referência de escaneamento para as demais classes.
 * @author Caio Lucena.
 *
 */
@Configuration
@ComponentScan(basePackageClasses = CadastroGrupoService.class)
public class ServiceConfig {
	
	
	
}
