package fr.morgan.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.morgan.webapp.service.DbuserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
										.requestMatchers("/home/**")
										.permitAll()
										.requestMatchers("/user/**")
										.hasRole("USER")
										.requestMatchers("/admin/**")
										.hasRole("ADMIN")
										.anyRequest()
										.authenticated())
										.formLogin((login) -> login.loginPage("/login")
												.defaultSuccessUrl("/home")
												.permitAll())
										.logout((logout) -> logout.permitAll()
												.logoutUrl("/logout")
												.logoutSuccessUrl("/logoutsuccess")
												.invalidateHttpSession(true)
												.clearAuthentication(true)
												.deleteCookies("JSESSIONID"));
		return http.build();
	}

	/* STOCKAGE DES UTILISATEURS EN MEMOIRE */
// 	@Bean
// 	public UserDetailsService userDetailsService() {
// 		UserDetails user = User
// 			.withUsername("user")
// 			.password(passwordEncoder().encode("user"))
// 			.roles("USER")
// 			.build();
// 		UserDetails admin = User
// 			.withUsername("admin")
// 			.password(passwordEncoder().encode("admin"))
// 			.roles("ADMIN", "USER")
// 			.build();
// 		return new InMemoryUserDetailsManager(user, admin);  // Stockage des user en mémoire
// 		
// 	}
	@Bean
	protected AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);

		return authenticationManagerBuilder.build();
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
