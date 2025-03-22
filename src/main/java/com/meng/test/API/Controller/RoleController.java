package com.meng.test.API.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.RoleDTO;
import com.meng.test.API.Entity.Role;
import com.meng.test.API.Mapper.RoleMapper;
import com.meng.test.API.Service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
	
	private final RoleService roleService;
	
	@PreAuthorize("hasAuthority('role:write')")
	@PostMapping
	public ResponseEntity<?> createRole(@RequestBody RoleDTO dto){
		Role role = roleService.createRole(RoleMapper.INTANCE.toRole(dto));
		return ResponseEntity.ok(RoleMapper.INTANCE.toDTO(role));
	}
	
}
