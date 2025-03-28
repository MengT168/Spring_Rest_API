package com.meng.test.API.Repostitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.test.API.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	

	Optional<User> getByUsername(String username);
	
}
