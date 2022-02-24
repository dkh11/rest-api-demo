package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	@Query(value = "SELECT u FROM Author u where u.userName = ?1 and u.password = ?2 ")
    Optional<Author> login(String username,String password);

    Optional<Author> findByToken(String token);
}
