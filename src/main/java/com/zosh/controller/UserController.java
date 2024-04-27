package com.zosh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zosh.models.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	

	
	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users=userRepository.findAll();
		
		return users;
		
	}
	
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		
		User user = userService.findUserById(id);
		return user;
		
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User updatedUser =userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
		 
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId2) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user=userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users=userService.searchUser(query);
		
		return users;
	}
	@GetMapping("/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt){
//		String email =
//		System.out.println("jwt ---- " +jwt );
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}

}
