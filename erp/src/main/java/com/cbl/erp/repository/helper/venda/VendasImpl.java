package com.cbl.erp.repository.helper.venda;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.time.Year;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cbl.erp.repository.helper.VendasQueries;

public class VendasImpl implements VendasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public BigDecimal valorTotalNoAno() {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal) from Venda where year(dataCriacao) = :ano", BigDecimal.class)
					.setParameter("ano", Year.now().getValue())
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal valorTotalNoMes() {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal) from Venda where month(dataCriacao) = :mes", BigDecimal.class)
					.setParameter("mes", MonthDay.now().getMonthValue())
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
}
