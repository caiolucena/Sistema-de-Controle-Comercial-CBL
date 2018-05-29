package com.cbl.erp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cbl.erp.model.Cidade;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String codigo) {
		System.out.println("ENTREI NO CEONVERSOR!!!" + codigo);
		if (!StringUtils.isEmpty(codigo)) {
			System.out.println("DEU CERTO O CONVERSOR HEIN!!! "+ codigo);
			Cidade cidade = new Cidade(); 
			cidade.setId(Integer.valueOf(codigo));
			return cidade;
		}
		
		return null;
	}

}
