package tutorial.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration that ensures only authenticated users can see some pages. If Spring Security is on the classpath, 
 * Spring Boot automatically secures all HTTP endpoints with “basic” authentication.<br/>
 * <br/>
 * It is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/**
	 * The SecurityFilterChain bean defines which URL paths should be secured and which should not. Specifically, the /security_home path 
	 * is configured to not require any authentication. All other paths must be authenticated.<br/>
	 * <br/>
	 * When a user successfully logs in, they are redirected to the previously requested page that required authentication. There is a custom 
	 * /security_login page (which is specified by loginPage()), and everyone is allowed to view it.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/security_home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/security_login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	/**
	 * User.withDefaultPasswordEncoder() method is not considered safe for production, but is acceptable for demos and 
	 * getting started. For production purposes, ensure the password is encoded externally. See the method Javadoc for 
	 * additional details. There are no plans to remove this support. It is deprecated to indicate that this is considered 
	 * insecure for production purposes.
	 */
	@SuppressWarnings("deprecation")
	/**
	 * The UserDetailsService bean sets up an in-memory user store with a single user. That user is given a user name of "user", 
	 * a password of "password", and a role of "USER".
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
