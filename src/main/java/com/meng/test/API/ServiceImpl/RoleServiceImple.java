package com.meng.test.API.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Role;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Repostitory.RoleRepository;
import com.meng.test.API.Service.RoleService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RoleServiceImple implements RoleService {
	
	private final RoleRepository roleRepository;

	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role getById(Integer id) {
		Role role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role", id));
		return role;
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role updateRole(Integer id, Role role) {
		Role role1 = getById(id);
		role1.setName(role.getName());
		return role1;
	}

	@Override
	public void deleteRole(Integer id) {
		Role role = getById(id);
		roleRepository.delete(role);
	}

}
