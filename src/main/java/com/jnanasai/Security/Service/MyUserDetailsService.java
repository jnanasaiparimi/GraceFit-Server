package com.jnanasai.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jnanasai.Security.Model.UserPrincpal;
import com.jnanasai.Security.Model.Users;
import com.jnanasai.Security.Repo.Repo;

@Service
public class MyUserDetailsService implements UserDetailsService
{

	@Autowired
	private Repo repo;
	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
	    Users user = repo.findByUsername(input);
	    if (user == null) {
	        user = repo.findByEmail(input);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username or email: " + input);
	        }
	    }
	    return new UserPrincpal(user);
	}

	

	
}
