package com.jnanasai.Security.Controller;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jnanasai.Security.Model.Users;
import com.jnanasai.Security.Service.MainUserService;

import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.servlet.http.HttpSession;

@RestController
public class MainUserController 
{
	@Autowired
	private MainUserService service;
	
	

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user, HttpSession session) {
	    return service.verify(user, session);
	}


	
	@PostMapping("/register")
	public Users register(@RequestBody Users user)
	{
		return service.register(user);
	}
	
}
         