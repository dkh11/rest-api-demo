package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;

public class DefaultAuthorService implements AuthorInterface{

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public String login(String username, String password) {
        Optional<Author> author = authorRepository.login(username,password);
        if(author.isPresent()){
            String token = UUID.randomUUID().toString();
            Author auth = author.get();
            auth.setToken(token);
            authorRepository.save(auth);
            return token;
        }

        return StringUtils.EMPTY;
    }

	@Override
	public Optional<User> findByToken(String token) {
        Optional<Author> customer= authorRepository.findByToken(token);
        if(customer.isPresent()){
            Author customer1 = customer.get();
            User user= new User(customer1.getUserName(), customer1.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

	@Override
	public Author findById(Long id) {
		Optional<Author> author= authorRepository.findById(id);
        return author.orElse(null);
	}
	

}
