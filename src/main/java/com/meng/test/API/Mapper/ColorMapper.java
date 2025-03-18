package com.meng.test.API.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.meng.test.API.DTO.ColorDTO;
import com.meng.test.API.Entity.Color;

@Mapper
public interface ColorMapper {
	
	ColorMapper INSTAN = Mappers.getMapper(ColorMapper.class);

	Color toColor(ColorDTO colorDTO);
	
	ColorDTO toDTO(Color color);
	
}
