package com.darkonnen.posts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.darkonnen.posts.models.User;
import com.darkonnen.posts.repositories.RoleRepository;
import com.darkonnen.posts.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// Create
	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		userRepository.save(user);
//		System.out.println(user.toString() + " from UserService");

	}

	public void saveUserWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
		userRepository.save(user);
	}

	// READ ONE

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// READ ALL
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	// create

//	public void createUser(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		
//		userRepository.save(user);
//	}

	// update

	public void updateUser(User user) {
		userRepository.save(user);
	}

	// delete

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
