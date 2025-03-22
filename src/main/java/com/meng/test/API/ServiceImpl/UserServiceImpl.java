package com.meng.test.API.ServiceImpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Role;
import com.meng.test.API.Entity.User;
import com.meng.test.API.Exception.ApiException;
import com.meng.test.API.Repostitory.UserRepository;
import com.meng.test.API.Security.AuthUser;
import com.meng.test.API.Security.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Override
	public Optional<AuthUser> getByName(String name) {
		User user = repository.getByUsername(name)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Username Not Found"));
		AuthUser authUser = AuthUser.builder().username(user.getUsername()).authorities(getAuthorities(user.getRoles()))
				.password(user.getPassword()).build();

		return Optional.ofNullable(authUser);
	}

	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> role) {
		Set<SimpleGrantedAuthority> authorities1 = role.stream().map(r1 -> new SimpleGrantedAuthority("ROLE_"+r1.getName()))
				.collect(Collectors.toSet());
		Set<SimpleGrantedAuthority> authorities = role.stream().flatMap(r -> toStream(r)).collect(Collectors.toSet());
		authorities.addAll(authorities1);
		return authorities;
	}

	private Stream<SimpleGrantedAuthority> toStream(Role role) {
		return role.getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}

}
