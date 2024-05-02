package exeGemHub.gemhub.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import exeGemHub.gemhub.Entity.User;
import exeGemHub.gemhub.Entity.UserPrinciple;
import exeGemHub.gemhub.Repository.UserRepo;
import exeGemHub.gemhub.Service.UserService;
import jakarta.transaction.Transactional;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return UserPrinciple.build(user);
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
		
	}

	@Override
	public Iterable<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
