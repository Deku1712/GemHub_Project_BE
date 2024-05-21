package exeGemHub.gemhub.jwt;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

	private static final Long MAX_AGE = 3600L;
	private static final int CORS_FILTER_ORDER = -102;

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
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:5173");
//		config.setAllowedHeaders(Arrays.asList(
//				HttpHeaders.AUTHORIZATION,
//				HttpHeaders.CONTENT_TYPE,
//				HttpHeaders.ACCEPT));
//		config.setAllowedMethods(Arrays.asList(
//				HttpMethod.GET.name(),
//				HttpMethod.POST.name(),
//				HttpMethod.PUT.name(),
//				HttpMethod.DELETE.name()));
//		config.setMaxAge(MAX_AGE);
//		source.registerCorsConfiguration("/**", config);
//		return source;
//	}
//@Bean
//FilterRegistrationBean corsFilter() {
//	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	CorsConfiguration config = new CorsConfiguration();
//	config.setAllowCredentials(true);
//	config.addAllowedOrigin("http://localhost:3000");
//	config.setAllowedHeaders(Arrays.asList(
//			HttpHeaders.AUTHORIZATION,
//			HttpHeaders.CONTENT_TYPE,
//			HttpHeaders.ACCEPT));
//	config.setAllowedMethods(Arrays.asList(
//			HttpMethod.GET.name(),
//			HttpMethod.POST.name(),
//			HttpMethod.PUT.name(),
//			HttpMethod.DELETE.name()));
//	config.setMaxAge(MAX_AGE);
//	source.registerCorsConfiguration("/**", config);
//	FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//
//	bean.setOrder(CORS_FILTER_ORDER);
//	return bean;
//}

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
										.requestMatchers("/products").permitAll()
										.requestMatchers("/products/**").permitAll()
										.requestMatchers("/payment/vnpay-payment").permitAll()
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
