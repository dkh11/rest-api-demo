package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@RestController
public class AuthorJpaResource {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;
	
	// get all authors
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> retrieveAllAuthors() {
		return ResponseEntity.ok().body(authorRepository.findAll());
	}
//	public List<Author> retrieveAllAuthors() {
//		return authorRepository.findAll();
//	}
	
	
	// get one author
	@GetMapping("/authors/{id}")
	public EntityModel<Author> retrieveAuthor(@PathVariable Long id) {
		// pass it as a @QueryParam... 
		Optional<Author> author = authorRepository.findById(id);
		
		if (!author.isPresent()) {
			throw new EntityNotFoundException("id: " + id);
		}
	
		EntityModel<Author> resource = EntityModel.of(author.get());
		
		WebMvcLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).retrieveAllAuthors());
		
		resource.add(linkTo.withRel("all-authors"));
		return resource;
	}
	

	// create new author
	@PostMapping("/authors")
	public ResponseEntity<Object> createAuthor(@Valid @RequestBody Author author) {
		Author savedAuthor = authorRepository.save(author);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedAuthor.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	// list of all books of one author
	@GetMapping("/authors/{id}/books")
	public List<Book> retrieveAllBooks(@PathVariable Long id) {
		Optional<Author> authorOptional = authorRepository.findById(id);
	
		if (!authorOptional.isPresent()) {
			throw new EntityNotFoundException("id: " + id);
		}
		
		return authorOptional.get().getBooks();
	}
	
	
	// get details of a book by author id and book id
	// find author by book id - /authors?bookId={bId}... use @QueryParam...
	// 
	@GetMapping("/authors/{aId}/books/{bId}")
	public EntityModel<Book> retrieveBook(@PathVariable Long aId, @PathVariable int bId) {
		Optional<Author> author = authorRepository.findById(aId);
		
		if (!author.isPresent()) {
			throw new EntityNotFoundException("Author with id: " + aId + " not found.");
		}
		
		Optional<Book> book = bookRepository.findById(bId);
		
		if (!book.isPresent()) {
			throw new EntityNotFoundException("Book with id: " + bId + " not found");
		}
	
		EntityModel<Book> resource = EntityModel.of(book.get());
		
		WebMvcLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).retrieveAllBooks(aId));
		
		resource.add(linkTo.withRel("all-books-by-this-author"));
		return resource;
	}
}











































