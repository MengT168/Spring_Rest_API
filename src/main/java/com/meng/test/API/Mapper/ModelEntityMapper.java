package com.meng.test.API.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.meng.test.API.DTO.ModelDTO;
import com.meng.test.API.Entity.Model;
import com.meng.test.API.Service.BrandService;

@Mapper(componentModel = "spring" , uses = {BrandService.class})
public interface ModelEntityMapper {
	
	ModelEntityMapper INSTAN = Mappers.getMapper(ModelEntityMapper.class);
	
	@Mapping(target = "brand" , source = "brandId")
	@Mapping(target = "id" , ignore = true)
	Model toModel(ModelDTO dto);
	
	@Mapping(target = "brandId" , source = "brand.id")
	ModelDTO toDTO(Model model);
	
}
