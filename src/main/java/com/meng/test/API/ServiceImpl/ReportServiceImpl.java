package com.meng.test.API.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.Projection.ProductSold;
import com.meng.test.API.Repostitory.SaleRepository;
import com.meng.test.API.Service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final SaleRepository repository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return repository.findProductSold(startDate, endDate);
	}

}
