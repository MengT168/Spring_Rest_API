package com.meng.test.API.Service;

import java.time.LocalDate;
import java.util.List;

import com.meng.test.API.DTO.ProductExpenseDTO;

public interface ExpenseReportService {
	List<ProductExpenseDTO> getExpenseReport(LocalDate startDate , LocalDate endDate);
}
