package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message="Name should be more than 2 chars long.")
	private String name;
	
	private String userName;
	private String password;
	private String token;
	
	@OneToMany(mappedBy="author")
	private List<Book> books;
	
	public Author() {
		
	}
	
	public Author(Integer id, @Size(min = 2, message = "Name should be more than 2 chars long.") String name,
			String userName, String password, String token) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [userName=" + userName + ", password=" + password + "]";
	}

	
	
	
}
