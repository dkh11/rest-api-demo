package com.example.demo.service;

import org.springframework.security.core.userdetails.User;

import com.example.demo.entity.Author;

import java.util.Optional;

public interface AuthorInterface {
	String login(String username, String password);
    Optional<User> findByToken(String token);
    Author findById(Long id);
}
