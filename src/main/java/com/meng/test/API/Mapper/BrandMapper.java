package com.meng.test.API.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.meng.test.API.DTO.BrandDTO;
import com.meng.test.API.Entity.Brand;

@Mapper
public interface BrandMapper {
	
	BrandMapper INSTAND = Mappers.getMapper(BrandMapper.class);
	
	Brand toBrand(BrandDTO brandDTO);
	
	BrandDTO toBrandDTO(Brand brand);
}
