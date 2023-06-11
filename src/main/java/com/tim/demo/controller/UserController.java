package com.tim.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.demo.model.User;
import com.tim.demo.repository.UserRepository;
import com.tim.demo.exception.ResourceNotFoundException;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository; 
	
	//get all employees
	@GetMapping("/users")
	public List<User>getusers()
	{
		return userRepository.findAll();
	}
	//user registration	
		@PostMapping("/users")
		public User createUser(@RequestBody User user)
		{
			return userRepository.save(user);
		}
		
		
		
		
		@GetMapping("/users/{id}")
		public ResponseEntity<User> getUserById(@PathVariable Long id)
		{
			User user = userRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("User not found"+id));
			return ResponseEntity.ok(user);
		}
		@PutMapping("/users/{id}")
		public ResponseEntity<User> updateUser(Long id,@RequestBody User userDetails)
		{
			User user = userRepository.findById(id)
					.orElseThrow(()->new ResourceNotFoundException("User not found"));
			user.setFirstname(userDetails.getFirstname());
			user.setLastname(userDetails.getLastname());
			user.setEmailid(userDetails.getEmailid());
			
			User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
			
		}
		@DeleteMapping("/users/{id}")
		public  ResponseEntity<Map<String,Boolean>>deleteUser( @PathVariable Long id)
		{
			
			User user =userRepository.findById(id)
			.orElseThrow(()->new ResourceNotFoundException("User not found"));
			
			userRepository.delete(user);
			Map<String,Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
}
