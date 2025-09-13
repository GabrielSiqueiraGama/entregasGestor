package com.zhant.entregasGestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.zhant.entregasGestor.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	UserDetails findByLogin(String username);
}
