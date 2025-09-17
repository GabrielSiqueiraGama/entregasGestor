package com.zhant.entregasGestor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhant.entregasGestor.dto.LoginResponseDTO;
import com.zhant.entregasGestor.dto.UserDTO;
import com.zhant.entregasGestor.dto.UserRegisterDTO;
import com.zhant.entregasGestor.models.User;
import com.zhant.entregasGestor.repositories.UserRepository;
import com.zhant.entregasGestor.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid UserDTO user) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(user.username(), user.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userRegister) {
		if(this.userRepository.findByUsername(userRegister.username()) != null) return ResponseEntity.badRequest().build();
		
			String encryptedPassword = new BCryptPasswordEncoder().encode(userRegister.password());
			User user = new User(userRegister.username(), encryptedPassword, userRegister.role());
			
			this.userRepository.save(user);
			
		    return ResponseEntity.ok().build();
	  }
	}