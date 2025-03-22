package com.meng.test.API.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.PermissionDTO;
import com.meng.test.API.Entity.Permission;
import com.meng.test.API.Mapper.PermissionMapper;
import com.meng.test.API.Service.PermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/permission")
public class PermissionController {
	private final PermissionService permissionService;
	
	@PreAuthorize("hasAuthority('permission:write')")
	@PostMapping
	public ResponseEntity<?> createPermission(@RequestBody PermissionDTO permissionDTO){
		Permission permission = permissionService.create(PermissionMapper.INSTAN.toPermission(permissionDTO));
		return ResponseEntity.ok(permission);
	}
	
	
	@PreAuthorize("hasAuthority('permission:read')")
	@GetMapping
	public ResponseEntity<?> getPermission(){
		List<Permission> permission = permissionService.getPermission();
		return ResponseEntity.ok(permission);
	}
	
	@PreAuthorize("hasAuthority('permission:write')")	
	@PutMapping("{id}")
	public ResponseEntity<?> updatePermission(@PathVariable Integer id , @RequestBody PermissionDTO permissionDTO){
		Permission updatePermission = permissionService.updatePermission(id, PermissionMapper.INSTAN.toPermission(permissionDTO));
		return ResponseEntity.ok(PermissionMapper.INSTAN.toPermissionDTO(updatePermission));
	}
	
	@PreAuthorize("hasAuthority('permission:write')")	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletePermission(@PathVariable Integer id){
		permissionService.detelePermission(id);
		return ResponseEntity.ok().build();
	}
	
}
