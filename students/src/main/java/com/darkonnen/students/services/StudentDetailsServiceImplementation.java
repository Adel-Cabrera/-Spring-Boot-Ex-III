package com.darkonnen.students.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.darkonnen.students.models.Role;
import com.darkonnen.students.models.Student;
import com.darkonnen.students.models.User;
import com.darkonnen.students.repositories.StudentRepository;

@Service
public class StudentDetailsServiceImplementation implements UserDetailsService {
	
    private StudentRepository studentRepository;
    
    public StudentDetailsServiceImplementation(StudentRepository userRepository){
        this.studentRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println(username + " -> username");
//		User user = userRepository.findByUsername(username);
		Student student = studentRepository.findByEmail(username);
		
		if (student == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(),
				getAuthorities(student));
	}

	private List<GrantedAuthority> getAuthorities(Student student) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        for(Role role : student.getStudentRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
		return authorities;
	}
}
