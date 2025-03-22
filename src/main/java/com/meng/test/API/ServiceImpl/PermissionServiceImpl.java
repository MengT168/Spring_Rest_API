package com.meng.test.API.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Permission;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Repostitory.PermissionRepository;
import com.meng.test.API.Service.PermissionService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

	private final PermissionRepository permissionRepository;
	
	@Override
	public Permission create(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public List<Permission> getPermission() {
		return permissionRepository.findAll();
	}


	@Override
	public Permission getById(Integer id) {
		Permission permission = permissionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Permission", id));
		return permission;
	}

	@Override
	public Permission updatePermission(Integer id, Permission permission) {
		Permission permissionId = getById(id);
		permissionId.setName(permission.getName());
		return permissionRepository.save(permissionId);
	}

	@Override
	public void detelePermission(Integer id) {
		Permission permissionId = getById(id);
		permissionRepository.delete(permissionId);
	}
}
