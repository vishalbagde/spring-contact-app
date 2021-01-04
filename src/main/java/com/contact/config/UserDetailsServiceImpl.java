package com.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contact.entity.Users;
import com.contact.repository.UsersRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.getUserByName(username);
		if (users == null) {
			throw new UsernameNotFoundException("Could Not Found User");
		}
		CustomUsersDetails customUsersDetails = new CustomUsersDetails(users);
		return customUsersDetails;
	}

}
