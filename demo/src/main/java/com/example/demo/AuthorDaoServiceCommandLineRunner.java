//package com.example.demo;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.entity.Author;
//import com.example.demo.service.AuthorDaoService;
//
//@Component
//public class AuthorDaoServiceCommandLineRunner implements CommandLineRunner{
//
//	private static final Logger log =
//			LoggerFactory.getLogger(AuthorDaoServiceCommandLineRunner.class);
//	@Autowired
//	private AuthorDaoService authorDaoService;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		Author author = new Author(2, "author_DAO_Service");
//		long insert = authorDaoService.insert(author);
//	
//		log.info("New author is created: " + author);
//	}
//
//}
