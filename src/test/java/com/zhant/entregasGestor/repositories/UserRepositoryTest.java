package com.zhant.entregasGestor.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import com.zhant.entregasGestor.dto.UserDTO;
import com.zhant.entregasGestor.models.User;
import com.zhant.entregasGestor.models.UserRole;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("Should return user sucessfully")
	void testFindByUsernameSucess() {
		String nome = "Gabriel";
		UserDTO data = new UserDTO(nome, "Passowrd12!", UserRole.ADMIN);
		this.createUser(data);
		
		UserDetails userDetails = this.userRepository.findByUsername(nome);
		assertThat(userDetails).isNotNull();
	}

	
	private User createUser(UserDTO data) {
		User newUser = new User(data);
		this.entityManager.persist(newUser);
		return newUser;
	}
}
