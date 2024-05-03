package exeGemHub.gemhub.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import exeGemHub.gemhub.Entity.User;

public interface UserService extends UserDetailsService {
	void save(User user);

	Iterable<User> findAll();
	
	User findByUsername(String username);
}
