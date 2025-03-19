package com.meng.test.API.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.SaleDTO;
import com.meng.test.API.Service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {
	
	private final SaleService saleService;
	
	
	@PostMapping
	public ResponseEntity<?> sale(@RequestBody SaleDTO dto){
		saleService.sell(dto);
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("{id}/cancelSale")
	public ResponseEntity<?> cancelSale(@PathVariable Integer id){
		saleService.cancelSale(id);
		return ResponseEntity.ok().build();
	}
	
	
}
