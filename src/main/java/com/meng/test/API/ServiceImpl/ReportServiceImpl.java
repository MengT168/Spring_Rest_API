package com.meng.test.API.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.meng.test.API.DTO.ReportDTO;
import com.meng.test.API.Entity.Product;
import com.meng.test.API.Entity.Sale_detail;
import com.meng.test.API.Filter.ReportFilter;
import com.meng.test.API.Projection.ProductSold;
import com.meng.test.API.Repostitory.ProductRepository;
import com.meng.test.API.Repostitory.SaleDetailRepository;
import com.meng.test.API.Repostitory.SaleRepository;
import com.meng.test.API.Service.ReportService;
import com.meng.test.API.spec.ReportSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final SaleRepository repository;
	private final SaleDetailRepository saleDetailRepository;
	private final ProductRepository productRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return repository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
		
		List<ReportDTO> list = new ArrayList<>();
		ReportFilter reportFilter = new ReportFilter();
		reportFilter.setStartDate(startDate);
		reportFilter.setEndDate(endDate);
		Specification<Sale_detail> spec = new ReportSpec(reportFilter);
		List<Sale_detail> saleDetail = saleDetailRepository.findAll(spec);
		
		
		Map<Integer, Product> productMap = 
				productRepository.findAllById(saleDetail.stream().map(p->p.getProduct().getId()).toList())
								.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		Map<Product, List<Sale_detail>> saleDetailMap = saleDetail.stream().collect(Collectors.groupingBy(Sale_detail::getProduct));
		
		for(var entry : saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<Sale_detail> sd = entry.getValue();
			
			Integer unit = sd.stream().map(s->s.getUnit()).reduce(0,(a,b)->a+b);
			
			double totalAmount = sd.stream().mapToDouble(s->s.getAmount().doubleValue() * s.getUnit() ).sum();
			
			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			reportDTO.setTotalUnit(unit);
			
			
			list.add(reportDTO);
		}
		return list;
	}

}
