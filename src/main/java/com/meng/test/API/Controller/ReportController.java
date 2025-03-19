package com.meng.test.API.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.Projection.ProductSold;
import com.meng.test.API.Service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
	
	private final ReportService reportService;

	@GetMapping("/{startDate}/{endDate}")
	public ResponseEntity<?> productSoldReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate ,
												@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ProductSold> productSold = reportService.getProductSold(startDate, endDate);
		return ResponseEntity.ok(productSold);
	}
	
}
