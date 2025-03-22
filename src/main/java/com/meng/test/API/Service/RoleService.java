package com.meng.test.API.Service;

import java.util.List;

import com.meng.test.API.Entity.Role;

public interface RoleService {
	Role createRole(Role role);
	Role getById(Integer id);
	List<Role> getRoles();
	Role updateRole(Integer id , Role role);
	void deleteRole(Integer id);
}
