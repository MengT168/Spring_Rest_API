package com.meng.test.API.Service;

import java.util.List;

import com.meng.test.API.Entity.Permission;

public interface PermissionService {
	Permission create(Permission permission);
	List<Permission> getPermission();
	Permission getById(Integer id);
	Permission updatePermission(Integer id , Permission permission);
	void detelePermission(Integer id);
}
