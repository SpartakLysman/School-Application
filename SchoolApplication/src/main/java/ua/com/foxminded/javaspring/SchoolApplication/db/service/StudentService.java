package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public boolean addStudentToCourse(Student student, long courseId) {

		Course course = new Course();

		boolean isStudentNotOnCourse = !course.getStudents().contains(student);
		boolean isCourseExist = courseRepository.findById(courseId).isPresent();
		boolean isStudentExist = studentRepository.findById(student.getKey()).isPresent();

		if (isStudentExist && isCourseExist && isStudentNotOnCourse) {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			if (courseOptional.isPresent()) {
				Course course1 = courseOptional.get();
				course1.addStudent(student);
				student.deleteCourse(course1);
				studentRepository.save(student);
			} else {
				System.out.println("Course not found");
			}
		} else {
			System.out.println("Some problems");
		}

		return true;
	}

	public boolean deleteStudentFromCourse(Student student, long courseId) {

		Course course = new Course();

		boolean isCourseExist = courseRepository.findById(courseId).isPresent();
		boolean isStudentExist = studentRepository.findById(student.getKey()).isPresent();
		boolean isStudentOnCourse = course.getStudents().contains(student);

		if (isStudentExist && isCourseExist && isStudentOnCourse) {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			if (courseOptional.isPresent()) {
				Course course1 = courseOptional.get();
				course1.deleteStudent(student);
				student.deleteCourse(course1);
				studentRepository.save(student);
			} else {
				System.out.println("Course not found");
			}
		} else {
			System.out.println("Some problems");
		}

		return true;
	}

	public boolean delete(Student student) {

		LOGGER.debug("Student deleting - " + student.toString());
		boolean deleted = studentRepository.deleteStudent(student);
		LOGGER.info("Student was successfully deleted with id - " + student.getKey());

		return deleted;
	}

	public boolean update(Student student) {

		LOGGER.debug("Student updating - " + student.toString());
		try {
			studentRepository.save(student);
			LOGGER.info("Student was successfully updated with id - " + student.getKey());
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
