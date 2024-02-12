package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.StudentRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@Repository
@Transactional
public class PostgreSqlStudentDao {

	public final StudentRepository studentRepository;

	private static Logger log = Logger.getLogger(PostgreSqlStudentDao.class.getName());

	@Autowired
	public PostgreSqlStudentDao(StudentRepository studentRepository) {

		this.studentRepository = studentRepository;
	}

//	public Student login(String login, String password) throws DaoException {
//		try {
//			log.config("Looking for student with the login: " + login + " and password: " + password);
//
//			return studentRepository.findByLoginAndPassword(login, password)
//					.orElseThrow(() -> new NoResultException("No student found for the given login and password"));
//		} catch (NoResultException e) {
//			System.out.println("Some problems with your data :(");
//			return null;
//		}
//
//	}

	public boolean create(Student student) {
		try {
			studentRepository.save(student);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean createAll(List<Student> studentsList) {
		try {
			studentRepository.saveAll(studentsList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteStudent(Student student) {
		try {
			studentRepository.delete(student);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Student student) {
		try {
			studentRepository.save(student);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}

	public Optional<Student> findMaxId() {

		return studentRepository.findFirstByOrderByKeyDesc();

	}

	public Optional<Student> findById(Long key) {
		return studentRepository.findById(key);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public boolean ifExistFindById(Long key) {
		return studentRepository.existsById(key);
	}
}
