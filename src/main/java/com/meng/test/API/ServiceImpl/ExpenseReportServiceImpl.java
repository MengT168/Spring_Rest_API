package com.meng.test.API.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.meng.test.API.DTO.ProductExpenseDTO;
import com.meng.test.API.Entity.Product;
import com.meng.test.API.Entity.ProductImportHistory;
import com.meng.test.API.Filter.ExpenseReportFilter;
import com.meng.test.API.Repostitory.ProductImportHistoryRepository;
import com.meng.test.API.Repostitory.ProductRepository;
import com.meng.test.API.Service.ExpenseReportService;
import com.meng.test.API.spec.ExpenseReportSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseReportServiceImpl implements ExpenseReportService {
	
	private final ProductImportHistoryRepository productImportHistoryRepository;
	private final ProductRepository productRepository;

	@Override
	public List<ProductExpenseDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
		
		List<ProductExpenseDTO> list = new ArrayList<>();
		
		ExpenseReportFilter expenseReportFilter = new ExpenseReportFilter();
		expenseReportFilter.setStartDate(startDate);
		expenseReportFilter.setEnDate(endDate);
		Specification<ProductImportHistory> spec = new ExpenseReportSpec(expenseReportFilter);
		List<ProductImportHistory> productImport = productImportHistoryRepository.findAll(spec);
		
		Set<Integer> productId = productImport.stream().map(pd->pd.getProduct().getId()).collect(Collectors.toSet());
		List<Product> products = productRepository.findAllById(productId);
		Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		Map<Product, List<ProductImportHistory>> productImportMap = productImport.stream().collect(Collectors.groupingBy(ProductImportHistory::getProduct));
		
		
		
		for(var entry: productImportMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<ProductImportHistory> pi = entry.getValue();
			Integer unit = pi.stream().map(p->p.getImport_unit()).reduce(0,(a,b)->a+b);
			
			double totalAmount = pi.stream().mapToDouble(p->p.getPrice_per_unit().doubleValue() * p.getImport_unit()).sum();
			
			ProductExpenseDTO expenseDTO = new ProductExpenseDTO();
			
			expenseDTO.setProductId(product.getId());
			expenseDTO.setProductName(product.getName());
			expenseDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			expenseDTO.setTotalUnit(unit);
			
			list.add(expenseDTO);
			
			
			
		}
		
		
		
		return list;
	}

}
