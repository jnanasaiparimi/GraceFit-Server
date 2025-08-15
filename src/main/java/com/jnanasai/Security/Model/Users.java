package com.jnanasai.Security.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users 
{
	@Id
	private int id;
	private String username;
	private String password;
	private String role; 
	@Column(unique = true)
	private String email;
    
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}
	
		public void setRole(String role) {
		    if (role == null) {
		        this.role = null;
		        return;
		    }

		    String normalizedRole = role.trim().toUpperCase();

		    // Ensure it has ROLE_ prefix
		    if (!normalizedRole.startsWith("ROLE_")) {
		        normalizedRole = "ROLE_" + normalizedRole;
		    }

		    this.role = normalizedRole;
		}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", email="
				+ email + "]";
	}
	
}
