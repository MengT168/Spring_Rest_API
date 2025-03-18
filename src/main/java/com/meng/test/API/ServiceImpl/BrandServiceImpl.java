package com.meng.test.API.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Brand;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Repostitory.BrandRepository;
import com.meng.test.API.Service.BrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
	
	private final BrandRepository brandRepository;

	@Override
	public Brand createBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getById(Integer id) {
		Brand brand = brandRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("brand", id));
		return brand;
	}

	@Override
	public Brand updateBrand(Integer id, Brand brand) {
		Brand brand1 = getById(id);
		brand1.setBrandName(brand.getBrandName());
		return brandRepository.save(brand1);
	}

	@Override
	public void deleteById(Integer id) {
		brandRepository.deleteById(id);
	}

}
