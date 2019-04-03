package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id ;
	
	public String name;
	public String lastName;
	public String logIn;
	public String password;
	public String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires = new HashSet<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogIn() {
		return logIn;
	}
	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String lastName, String logIn, String password, String email) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.logIn = logIn;
		this.password = password;
		this.email = email;
	}
	
	
	

}
