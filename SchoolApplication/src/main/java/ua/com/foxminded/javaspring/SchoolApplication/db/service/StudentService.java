package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.User;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class StudentService {

	@Autowired
	private PostgreSqlStudentDao studentRepository;

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@RequestMapping("/")
	public String index() {
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");

		return "Student Service";
	}

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
