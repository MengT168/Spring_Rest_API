package com.meng.test.API.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.meng.test.API.Entity.ProductImportHistory;
import com.meng.test.API.Filter.ExpenseReportFilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class ExpenseReportSpec implements Specification<ProductImportHistory> {
	
	private List<Predicate> predicates = new ArrayList<>();
	private final ExpenseReportFilter expenseReportFilter;

	@Override
	public Predicate toPredicate(Root<ProductImportHistory> productImportHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		
		if(Objects.nonNull(expenseReportFilter.getStartDate())) {
			Predicate startDate = cb.greaterThanOrEqualTo(productImportHistory.get("date_import"), expenseReportFilter.getStartDate());
			predicates.add(startDate);
		}
		if(Objects.nonNull(expenseReportFilter.getEnDate())) {
			Predicate endDate = cb.greaterThanOrEqualTo(productImportHistory.get("date_import"), expenseReportFilter.getEnDate());
			predicates.add(endDate);
		}
		
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
