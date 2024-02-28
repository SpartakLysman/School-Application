package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.CourseRepository;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.StudentRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	private final CourseRepository courseRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public boolean create(Student student) {
		LOGGER.debug("Student creating...");
		try {
			studentRepository.save(student);
			LOGGER.info("Students was successfully created" + student.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean createAll(List<Student> studentsList) {
		LOGGER.debug("Student creating...");
		try {
			studentRepository.saveAll(studentsList);
			LOGGER.info("All students were successfully created" + studentsList.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addStudentToCourse(Student student, long courseId) {
		try {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			Optional<Student> studentOptional = studentRepository.findById(student.getId());

			if (courseOptional.isPresent() && studentOptional.isPresent()) {
				Course course = courseOptional.get();
				Student updatedStudent = studentOptional.get();

				course.addStudent(updatedStudent);
				courseRepository.save(course);

				LOGGER.info("Student added to the course successfully.");
				return true;
			} else {
				LOGGER.error("Course or student not found.");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error adding student to the course.", e);
			return false;
		}
	}

	@Transactional
	public boolean deleteStudentFromCourse(Student student, long courseId) {
		try {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			Optional<Student> studentOptional = studentRepository.findById(student.getId());

			if (courseOptional.isPresent() && studentOptional.isPresent()) {
				Course course = courseOptional.get();
				Student updatedStudent = studentOptional.get();

				// Remove the student from the course and save the changes
				course.deleteStudent(updatedStudent);
				courseRepository.save(course);

				LOGGER.info("Student removed from the course successfully.");
				return true;
			} else {
				LOGGER.error("Course or student not found.");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error removing student from the course.", e);
			return false;
		}
	}

	@Transactional
	public int delete(Student student) {
		LOGGER.debug("Student deleting - " + student.toString());
		int deleted = 0;

		try {
			deleted = studentRepository.deleteStudent(student);
			LOGGER.info("Student was successfully deleted with id - " + student.getId());
		} catch (Exception e) {
			LOGGER.error("Error while deleting student", e);
		}
		return deleted;
	}

	public boolean update(Student student) {
		LOGGER.debug("Student updating - " + student.toString());
		try {
			studentRepository.save(student);
			LOGGER.info("Student was successfully updated with id - " + student.getId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Student> findByName(String name) {
		LOGGER.debug("Student findind by name");
		List<Student> studentsList = studentRepository.findByName(name);
		LOGGER.info("Students were successfully found by name - " + name);

		return studentsList;
	}

	public Optional<Student> findStudentWithMaxKey() {
		LOGGER.debug("The latest student id findind...");
		Optional<Student> latestId = studentRepository.findFirstByOrderByKeyDesc();
		LOGGER.info("The latest student id was found");

		return latestId;
	}

	public Optional<Student> findById(long key) {

		LOGGER.debug("Student finding by id");
		Optional<Student> student = studentRepository.findById(key);
		LOGGER.info("Student was successfully found by id - " + key);

		return student;
	}

	public List<Student> findAll() {
		LOGGER.debug("All student findind...");
		List<Student> studentsList = studentRepository.findAll();
		LOGGER.info("All students were successfully found");

		return studentsList;
	}
}
