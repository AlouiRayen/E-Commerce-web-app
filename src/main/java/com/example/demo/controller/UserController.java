package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@RestController
public class UserController {
	public List<User> users = new ArrayList<>();
	@Autowired
	UserRepository userRepository;

	@GetMapping("/hi")
	public String hello() {
		return "empty collection";
	}

	// afficher un seul user
	@GetMapping("/user/{Id}")
	public Optional<User> getUser(@PathVariable("Id") int userId,Pageable page) {
		
	return	userRepository.findById(userId);
		
	}

//show all
	@GetMapping("/users")
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	// create
	@PostMapping("/users")
	public User createuser(@RequestBody User user) {

		userRepository.save(user);
		return user;
	}

	// edit
	@PutMapping("/user/{Id}")
	public User updateUser(@PathVariable("Id") int userId, @RequestBody User newUser) {
		if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
		User user ;
      user=  userRepository.getOne(userId);
    		 
        	user.setEmail(newUser.email);
        	user.setLastName(newUser.lastName);
        	user.setLogIn(newUser.logIn);
        	user.setName(newUser.name);
        	user.setPassword(newUser.password);
        	user.setId(userId);
        	
        	return userRepository.save(user);
        }

	// delete
	@DeleteMapping("/user/d/{Id}")
	public void deleteUser(@PathVariable("Id") int userId) {
		if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
		userRepository.deleteById(userId);
		

	}

}
