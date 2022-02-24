package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;

@Component
public class AuthorRepositoryCommandLineRunner implements CommandLineRunner{

	private static final Logger log =
			LoggerFactory.getLogger(AuthorRepositoryCommandLineRunner.class);
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Author author = new Author(1, "author_Repository", "author_username", "author_password", "some_token");
		authorRepository.save(author);
		log.info("New author is created: " + author);
		
		Optional<Author> authorWithOne = authorRepository.findById(1L);
		log.info("Author with id 1 is retrieved: " + authorWithOne);
	
		List<Author> authors = authorRepository.findAll();
		log.info("All Authors: " + authors);
	}

}
