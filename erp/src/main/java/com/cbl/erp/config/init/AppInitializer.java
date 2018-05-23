package com.cbl.erp.config.init;


import javax.servlet.Filter;

import org.springframework.security.access.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cbl.erp.config.JPAConfig;
import com.cbl.erp.config.ServiceConfig;
import com.cbl.erp.config.WebConfig;

/**
 * Essa classe é responsável pelas configurações de inicialização. Serve para designar onde estão as configurações, em ordem de prioridade..
 * @author Caio Lucena
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
		
	/**
	 * Esse método é responsável por configurar o JPA e o ServiceConfig.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//return new Class<?>[] { H2Config.class, ServiceConfig.class };
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class};
	}

	/**
	 * Esse método é responsável por configurar o WebConfig.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
	
	/**
	 * Esse método é responsável por realizar o mapeamento dos endereços das views pelas barras.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	/**
	 * Esse método é responsável por definir a configuração da codificação de caracteres utilizadas na view.
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
		
        return new Filter[] { characterEncodingFilter };
	}

}
