package com.techprimers.security.springsecurityauthserver;

import com.techprimers.security.springsecurityauthserver.config.CustomUserDetail;
import com.techprimers.security.springsecurityauthserver.domain.model.Role;
import com.techprimers.security.springsecurityauthserver.domain.model.User;
import com.techprimers.security.springsecurityauthserver.domain.repositories.UserRepository;
import com.techprimers.security.springsecurityauthserver.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringSecurityAuthServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthServerApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service) throws Exception {
		//Setup a default user if db is empty
		if (repository.count() < 2) {
			service.save(new User("dba", "dba", Arrays.asList(new Role("DBA"))));
		}
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}
	/**
	 * We return an istance of our CustomUserDetails.
	 * @param repository
	 * @return
	 */
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetail(repository.findByUsername(username));
	}

}
