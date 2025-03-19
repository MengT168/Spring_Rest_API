package com.meng.test.API.Service;

import java.time.LocalDate;
import java.util.List;

import com.meng.test.API.Projection.ProductSold;

public interface ReportService {
	List<ProductSold> getProductSold(LocalDate startDate , LocalDate endDate);
}
