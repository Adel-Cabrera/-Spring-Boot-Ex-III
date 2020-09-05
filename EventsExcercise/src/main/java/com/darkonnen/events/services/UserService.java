package com.darkonnen.events.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkonnen.events.models.User;
import com.darkonnen.events.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; // Se llama a los métodos expuestos del reposotorio.
	
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
//    public User findByApodo(String apodo) {
//    	return userRepository.findByApodo(apodo);
//    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    public boolean authenticateUser(String email, String password) {

    	// encuentra al usuario por email
        User user = userRepository.findByEmail(email);

        // retorna falso si no lo encuentra por email
        if(user == null) {
            return false;
        } else {
        	// retorna true si la password hace match
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }	

}
