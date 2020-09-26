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
import com.darkonnen.students.models.Teacher;
import com.darkonnen.students.repositories.TeacherRepository;

@Service
public class TeacherDetailsServiceImplementation implements UserDetailsService {
	
    private TeacherRepository teacherRepository;
    
    public TeacherDetailsServiceImplementation(TeacherRepository userRepository){
        this.teacherRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println(username + " -> username");
//		User user = userRepository.findByUsername(username);
		Teacher teacher = teacherRepository.findByEmail(username);
		
		if (teacher == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(teacher.getEmail(), teacher.getPassword(),
				getAuthorities(teacher));
	}

	private List<GrantedAuthority> getAuthorities(Teacher teacher) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        for(Role role : teacher.getTeacherRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
		return authorities;
	}
}
