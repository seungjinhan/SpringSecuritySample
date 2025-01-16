package com.example.demo.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

	private final CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = this.customerRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User details not found for the user : " + username));

		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
		return new User(customer.getEmail(), customer.getPwd(), authorities);
	}

}
