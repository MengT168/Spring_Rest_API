package com.meng.test.API.Security;

import java.util.Optional;

public interface UserService {
	Optional<AuthUser> getByName(String name);
}
