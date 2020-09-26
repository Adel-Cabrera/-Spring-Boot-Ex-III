package com.darkonnen.students.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.darkonnen.students.models.Student;
import com.darkonnen.students.repositories.RoleRepository;
import com.darkonnen.students.repositories.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public StudentService(StudentRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.studentRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// Create
	public void saveWithUserRole(Student student) {
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		student.setRoles(roleRepository.findByName("ROLE_STUDENT"));
		studentRepository.save(student);
//		System.out.println(user.toString() + " from UserService");

	}

//	public void saveUserWithAdminRole(Student student) {
//		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
//		student.setRoles(roleRepository.findByName("ROLE_ADMIN"));
//		studentRepository.save(student);
//	}

	// READ ONE

	public Student findStudentByname(String username) {
		return studentRepository.findByUsername(username);
	}

	public Student findStudentById(Long id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}

	public Student findStudentByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	// READ ALL
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}


	// update

	public void updateStudent(Student student) {
		studentRepository.save(student);
	}

	// delete

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

}
