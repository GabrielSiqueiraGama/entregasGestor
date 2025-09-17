package com.zhant.entregasGestor.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity(name = "users")
@Getter
@Setter
public class User implements UserDetails{
/*	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	login TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL
	role TEXT NOT NULL*/
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
	private int id;
    @NotNull
	private String username;
    @NotNull
	private String password;
    @NotNull
	private UserRole role;
    

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));//Caso seja admin, vai ter os direitos de admnin e de usuario também.
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public User(int id, @NotNull String username, @NotNull String password, @NotNull UserRole role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
    public User(String username, String password, UserRole role) {
    	this.username = username;
    	this.password = password;
    	this.role = role;
    }

	public User() {
		super();
	}
} 