package com.info.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.entity.User;
import com.info.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	@CacheEvict(value = "userList", allEntries = true)
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Cacheable(value = "users", key = "#id")
	public User getUserById(int id) {
		System.out.println("Fetching from DB for ID: " + id);
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found for id: " + id));
	}

	@Cacheable(value = "userList")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional
	@Caching(evict = {
		    @CacheEvict(value = "users", key = "#id", beforeInvocation = true),
		    @CacheEvict(value = "userList", allEntries = true, beforeInvocation = true)
		})
	public User deleteUser(int id) {
		User dbUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		userRepository.deleteById(id);
		return dbUser;
	}

	@Transactional
	@CachePut(value = "users", key = "#user.id")          // update single user cache
	@CacheEvict(value = "userList", allEntries = true, beforeInvocation = true)    // clear list cache
	public User updateUser(User user) {
		User dbUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));


		dbUser.setUsername(user.getUsername());
		dbUser.setEmail(user.getEmail());

		return userRepository.save(dbUser); 
	}

}
