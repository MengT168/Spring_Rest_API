package com.meng.test.API.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.meng.test.API.Entity.Sale;
import com.meng.test.API.Entity.Sale_detail;
import com.meng.test.API.Filter.ReportFilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public class ReportSpec implements Specification<Sale_detail> {

	private ReportFilter filter;

	@Override
	public Predicate toPredicate(Root<Sale_detail> sale_detail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();

		Join<Sale_detail, Sale> sale = sale_detail.join("sale");

		if (Objects.nonNull(filter.getStartDate())) {
			cb.greaterThanOrEqualTo(sale.get("sold_date"), filter.getStartDate());
		}

		if (Objects.nonNull(filter.getEndDate())) {
			cb.greaterThanOrEqualTo(sale.get("sold_date"), filter.getEndDate());
		}

		predicates.add(cb.isTrue(sale.get("status")));

		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
