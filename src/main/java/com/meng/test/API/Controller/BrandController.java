package com.meng.test.API.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.BrandDTO;
import com.meng.test.API.Entity.Brand;
import com.meng.test.API.Mapper.BrandMapper;
import com.meng.test.API.Service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
	private final BrandService brandService;
	
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTAND.toBrand(brandDTO);
		brand = brandService.createBrand(brand);
		return ResponseEntity.ok(brand);
	}
	
	@GetMapping("/getBrands")
	public ResponseEntity<?> getAllBrand(){
		List<Brand> brands = brandService.getBrands();
		return ResponseEntity.ok(brands);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getBrand(@PathVariable int id){
		Brand brand = brandService.getById(id);
		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(brand));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable int id , @RequestBody BrandDTO dto){
		Brand brand = BrandMapper.INSTAND.toBrand(dto);
		brand = brandService.updateBrand(id, brand);
		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(brand));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		brandService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
