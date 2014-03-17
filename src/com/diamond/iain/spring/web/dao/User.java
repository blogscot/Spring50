package com.diamond.iain.spring.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.diamond.iain.spring.web.validation.ValidEmail;

public class User {
	
	@NotBlank
	@Size(min=5, max=15)
	@Pattern(regexp="^\\w{5,}$")
	private String username;

	@ValidEmail
	private String email;
	
	@NotBlank
	@Pattern(regexp="^\\S+$")
	@Size(min=6, max=15)
	private String password;
	
	private String authority;
	private boolean enabled = false;

	public User() {}
	
	public User(String username, String email, String authority,
			String password, boolean enabled) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.authority = authority;
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
