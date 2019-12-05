package com.mfinancials.security;

import com.mfinancials.security.user.UserRepository;
import com.mfinancials.security.user.model.User;
import com.mfinancials.security.user.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(new UserDetailsImpl("minde", "12345")));
		userRepository.save(new User(new UserDetailsImpl("jonas", "12345")));
	}
}
