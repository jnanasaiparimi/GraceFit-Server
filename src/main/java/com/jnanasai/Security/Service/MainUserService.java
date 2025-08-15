package com.jnanasai.Security.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.jnanasai.Security.Model.Users;
import com.jnanasai.Security.Repo.Repo;

import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.servlet.http.HttpSession;




@Service

public class MainUserService 
{
	@Autowired
	private Repo repo;
	
	@Autowired
	AuthenticationManager authManager;
	
	
	
	
	
	
	private BCryptPasswordEncoder passEncoder =new BCryptPasswordEncoder(12);
	
	
	
	public Users register(Users user)
	{
		user.setPassword(passEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public ResponseEntity<?> verify(Users user, HttpSession session) {
	    try {
	        Users foundUser = repo.findByUsername(user.getUsername());
	        if (foundUser == null) {
	            foundUser = repo.findByEmail(user.getUsername());
	        }

	        if (foundUser == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	        }

	        UsernamePasswordAuthenticationToken authToken =
	            new UsernamePasswordAuthenticationToken(foundUser.getUsername(), user.getPassword());

	        Authentication auth = authManager.authenticate(authToken); // will throw if invalid
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

	        LoginResponseDto dto = new LoginResponseDto(
	            foundUser.getUsername(),
	            foundUser.getEmail(),
	            foundUser.getRole()
	        );

	        return ResponseEntity.ok(dto);
	    } catch (Exception ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
	    }
	}




	

	}
