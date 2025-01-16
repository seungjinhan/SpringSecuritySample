package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		try {
			String hashPwd = this.passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			Customer saveCustomer = this.customerRepository.save(customer);
			if (saveCustomer.getId() > 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Successfuly register user");
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail register user");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception " + e.getMessage());
		}
	}
}
