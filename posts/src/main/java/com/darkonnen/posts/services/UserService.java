package com.darkonnen.posts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.darkonnen.posts.models.User;
import com.darkonnen.posts.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}

	// create

	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		
		userRepository.save(user);
	}

	// update

	public void updateUser(User user) {
		userRepository.save(user);
	}

	// delete

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
