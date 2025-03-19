package com.meng.test.API.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.ProductDTO;
import com.meng.test.API.DTO.ProductImportDTO;
import com.meng.test.API.DTO.SalePriceDTO;
import com.meng.test.API.Entity.Product;
import com.meng.test.API.Mapper.ProductMapper;
import com.meng.test.API.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class productController {
	
	private final ProductService productService;
	private final ProductMapper mapper;
	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto){
		Product product = mapper.toProduct(dto);
		product = productService.createProduct(product);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping
	public ResponseEntity<?> getProducts(){
		return ResponseEntity.ok(productService.getProducts());
	}
	
	@PostMapping("importProduct")
	public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO dto){
		productService.importProduct(dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/setSalePrice")
	public ResponseEntity<?> setSalePrice(@PathVariable Integer id ,  @RequestBody SalePriceDTO dto){
		productService.setSalePrice(id, dto.getSalePrice());
		return ResponseEntity.ok().build();
	}
	
}
