package com.meng.test.API.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.meng.test.API.DTO.RoleDTO;
import com.meng.test.API.Entity.Role;

@Mapper
public interface RoleMapper {
	
	RoleMapper INTANCE = Mappers.getMapper(RoleMapper.class);
	
	
	@Mapping(target = "id" , ignore = true)
	@Mapping(target = "permissions" , ignore = true)
	Role toRole(RoleDTO roleDTO);
	
	RoleDTO toDTO(Role role);
	
}
