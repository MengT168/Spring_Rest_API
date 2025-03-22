package com.meng.test.API.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.meng.test.API.DTO.PermissionDTO;
import com.meng.test.API.Entity.Permission;

@Mapper
public interface PermissionMapper {
	
	PermissionMapper INSTAN = Mappers.getMapper(PermissionMapper.class);
	
	
	@Mapping(target = "id" , ignore = true)
	Permission toPermission(PermissionDTO permissionDTO);
	
	
	PermissionDTO toPermissionDTO(Permission permission);
}
