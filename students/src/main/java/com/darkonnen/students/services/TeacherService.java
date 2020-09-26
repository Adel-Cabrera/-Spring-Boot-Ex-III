package com.darkonnen.students.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.darkonnen.students.models.Teacher;
import com.darkonnen.students.repositories.RoleRepository;
import com.darkonnen.students.repositories.TeacherRepository;

@Service
public class TeacherService {

	private TeacherRepository teacherRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public TeacherService(TeacherRepository teacherRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.teacherRepository = teacherRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// Create
	public void saveWithTeacherRole(Teacher teacher) {
		teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
		teacher.setTeacherRoles(roleRepository.findByName("ROLE_TEACHER"));
		teacherRepository.save(teacher);
//		System.out.println(user.toString() + " from UserService");

	}

//	public void saveUserWithAdminRole(Student student) {
//		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
//		student.setRoles(roleRepository.findByName("ROLE_ADMIN"));
//		studentRepository.save(student);
//	}

	// READ ONE

	public Teacher findTeacherByname(String username) {
		return teacherRepository.findByUsername(username);
	}

	public Teacher findTeacherById(Long id) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
		if (optionalTeacher.isPresent()) {
			return optionalTeacher.get();
		} else {
			return null;
		}
	}

	public Teacher findTeacherByEmail(String email) {
		return teacherRepository.findByEmail(email);
	}

	// READ ALL
	public List<Teacher> findAllTeachers() {
		return teacherRepository.findAll();
	}


	// update

	public void updateTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	// delete

	public void deleteTeacher(Long id) {
		teacherRepository.deleteById(id);
	}

}
