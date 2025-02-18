package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

//		http.authorizeHttpRequests((req) -> req.anyRequest().permitAll());
//		http.authorizeHttpRequests((req) -> req.anyRequest().denyAll());

		http.csrf(csrfConfig -> csrfConfig.disable());

		http.authorizeHttpRequests((req) -> req.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards")
				.authenticated().requestMatchers("/welcome", "/notices", "contact", "/error", "/register").permitAll());

		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());

//		http.formLogin(flc -> flc.disable());
//		http.httpBasic(hbc -> hbc.disable());

		return http.build();
	}

//	@Bean
//	UserDetailsService userDetailService(DataSource dataSource) {
////		UserDetails user = User.withUsername("user").password("{noop}Deepplin@1234").authorities("read").build();
////		UserDetails admin = User.withUsername("admin")
////				.password("{bcrypt}$2a$12$Hjudlnew3D9tKX6SpSxkDO/P7OzFB/VrR6AiobGQ.MjEss0lAinH6").authorities("admin")
////				.build();
//		return new JdbcUserDetailsManager(dataSource);
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
}
