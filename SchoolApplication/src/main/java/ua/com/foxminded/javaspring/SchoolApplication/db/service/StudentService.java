package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.User;

@Service
public class StudentService {

	@Autowired
	private PostgreSqlStudentDao studentRepository;

	public boolean create(Student student) {
		return studentRepository.create(student);
	}

	public int[] createAll(List<User> studentsList) {
		return studentRepository.createAll(studentsList);
	}

	public List<Entity> findAll() {
		return studentRepository.findAll();
	}

	public Student findById(long key) {

		return studentRepository.findById(key);
	}

	public List<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}

	public String delete(Student student) {
		studentRepository.delete(student);
		return "Student was succesfully removed!!";
	}

	public boolean update(Student student) {

		return studentRepository.update(student);
	}
}
