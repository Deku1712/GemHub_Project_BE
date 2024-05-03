package exeGemHub.gemhub.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exeGemHub.gemhub.Service.UserService;
import exeGemHub.gemhub.Service.impl.UserServiceImpl;

@Configuration
public class ServiceConfig {
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}
