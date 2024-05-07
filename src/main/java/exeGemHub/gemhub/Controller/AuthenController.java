package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.SignUp;
import exeGemHub.gemhub.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import exeGemHub.gemhub.Entity.User;
import exeGemHub.gemhub.Service.UserService;
import exeGemHub.gemhub.Service.impl.UserServiceImpl;
import exeGemHub.gemhub.jwt.JwtResponse;
import exeGemHub.gemhub.jwt.JwtService;


@RestController
@RequestMapping("/authen")
@CrossOrigin
public class AuthenController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepo userRepo;

	@PostMapping("/logIn")
	public ResponseEntity<?> login(@RequestBody User user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		System.out.println("lalal: " + user.toString());
		System.out.println(authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtService.generateTokenLogin(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User currentUser = userService.findByUsername(user.getUsername());

		return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getEmail(), userDetails.getAuthorities()));
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody SignUp signUp){
		if (userService.findByUsername(signUp.getUsername()) != null) {
			return ResponseEntity.badRequest().body("Tên người dùng đã được sử dụng!");
		}

		String encodePassword = passwordEncoder.encode(signUp.getPassword());
		User user = new User();
		user.setUsername(signUp.getUsername());
		user.setPassword(encodePassword);
		userRepo.save(user);

		return ResponseEntity.ok("Người dùng đã được tạo thành công: " + user.getUsername());

	}


}
