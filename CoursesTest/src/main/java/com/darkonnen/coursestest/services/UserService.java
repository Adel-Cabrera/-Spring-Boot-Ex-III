package com.darkonnen.coursestest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.darkonnen.coursestest.entities.User;
import com.darkonnen.coursestest.repositories.RoleRepository;
import com.darkonnen.coursestest.repositories.UserRepository;

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
		return userRepository.findUserByEmail(email);
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
