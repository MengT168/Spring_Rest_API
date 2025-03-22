package com.meng.test.API.Security;

import static com.meng.test.API.Security.PermissionEnum.BRAND_READ;
import static com.meng.test.API.Security.PermissionEnum.BRAND_WRITE;
import static com.meng.test.API.Security.PermissionEnum.COLOR_READ;
import static com.meng.test.API.Security.PermissionEnum.COLOR_WRITE;
import static com.meng.test.API.Security.PermissionEnum.EXPENCEREPORT_READ;
import static com.meng.test.API.Security.PermissionEnum.MODEL_READ;
import static com.meng.test.API.Security.PermissionEnum.MODEL_WRITE;
import static com.meng.test.API.Security.PermissionEnum.PRODUCT_READ;
import static com.meng.test.API.Security.PermissionEnum.PRODUCT_WRITE;
import static com.meng.test.API.Security.PermissionEnum.REPORT_READ;
import static com.meng.test.API.Security.PermissionEnum.PERMISSION_WRITE;
import static com.meng.test.API.Security.PermissionEnum.PERMISSION_READ;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {

	ADMIN(Set.of(BRAND_WRITE, BRAND_READ, MODEL_WRITE, MODEL_READ, COLOR_WRITE, PRODUCT_WRITE, PRODUCT_READ,
			REPORT_READ, EXPENCEREPORT_READ , PERMISSION_WRITE  , PERMISSION_READ)),
	SALE(Set.of(BRAND_READ, MODEL_READ , COLOR_READ));

	private Set<PermissionEnum> permissions;

	public Set<SimpleGrantedAuthority> grantedAuthority() {
		Set<SimpleGrantedAuthority> grantedAuthority = this.permissions.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getDescription())).collect(Collectors.toSet());
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.name());
		grantedAuthority.add(role);
		return grantedAuthority;
	}

}
