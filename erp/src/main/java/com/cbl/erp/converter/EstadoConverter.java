package com.cbl.erp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cbl.erp.model.Estado;

public class EstadoConverter implements Converter<String, Estado> {

	@Override
	public Estado convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Estado estado = new Estado();
			estado.setId(Integer.valueOf(codigo));
			return estado;
		}
		
		return null;
	}

}
