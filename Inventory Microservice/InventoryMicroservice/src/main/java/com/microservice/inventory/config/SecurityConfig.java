package com.microservice.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	 @Bean
	     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf(csrf -> csrf.disable())
         .cors(cors->cors.disable())
         .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
         .httpBasic(Customizer.withDefaults());
	        return http.build();
	    }

	   

	    @Bean
	     UserDetailsService userDetailsService() {
	        
	        UserDetails user1 =User.builder().username("Rishav Raj")
	        		.password(passwordEncoder.encode("Rishav@123")).roles("Admin")
	        		.build();
	        return new InMemoryUserDetailsManager(user1);
	    }

}
