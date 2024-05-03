package exeGemHub.gemhub.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import exeGemHub.gemhub.Service.UserService;
import exeGemHub.gemhub.Service.impl.UserServiceImpl;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserService userService;


	private  AuthenticationProvider authenticationProvider;

//    @Autowired
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

		authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        
		return new ProviderManager(authenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(10);

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().ignoringRequestMatchers("/**");
//
//		http.authorizeRequests().requestMatchers("/", "/authen/login","/authen/signUp").permitAll().anyRequest().authenticated().and().csrf()
//				.disable()
//				.logout(logout -> logout.logoutUrl("/logout").addLogoutHandler(new SecurityContextLogoutHandler()));
//
//		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		http.cors();

		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req ->
								req.requestMatchers("/authen/signUp").permitAll()
										.requestMatchers("/authen/logIn").permitAll()
										.requestMatchers("/cart/**").permitAll()
										.requestMatchers("/products/**").permitAll()
										.anyRequest()
										.authenticated()
				);

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .
		;

		return http.build();
	}

}
