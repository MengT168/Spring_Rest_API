package com.meng.test.API.Service;

import java.util.List;

import com.meng.test.API.Entity.Brand;

public interface BrandService {
	Brand createBrand(Brand brand);
	List<Brand> getBrands();
	Brand getById(Integer id);
	Brand updateBrand(Integer id , Brand brand);
	void deleteById(Integer id);
}
