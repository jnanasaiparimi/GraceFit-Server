package com.jnanasai.Security.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jnanasai.Security.Model.Users;

@Repository

public interface Repo extends JpaRepository<Users, Integer>
{
	Users findByUsername(String username);
	Users findByEmail(String email);
}
