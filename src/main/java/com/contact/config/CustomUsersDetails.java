package com.contact.config;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.contact.entity.Users;

public class CustomUsersDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119042417160603898L;
	private Users users;
	
	
	
	public CustomUsersDetails(Users users) {
		super();
		this.users = users;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.users.getRole());
		 return new ArrayList<GrantedAuthority>(Arrays.asList(simpleGrantedAuthority));
		 
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
