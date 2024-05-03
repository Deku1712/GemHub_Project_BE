package exeGemHub.gemhub.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private int id;
	private String token;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> roles;

	public JwtResponse(String token, int id, String type, String username,
			Collection<? extends GrantedAuthority> roles) {
		super();
		this.id = id;
		this.token = token;
		this.type = type;
		this.username = username;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

}
