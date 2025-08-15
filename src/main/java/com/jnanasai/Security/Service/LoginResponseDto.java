package com.jnanasai.Security.Service;

public class LoginResponseDto {
    private String username;
    private String email;
    private String role;

    public LoginResponseDto(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
