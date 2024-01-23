package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class StudentService {

	private PostgreSqlStudentDao studentRepository;
	private CourseService courseService;
	private Student student;

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public StudentService() {
		this.student = new Student();
	}

	public boolean create(Student student) {

		LOGGER.debug("Student creating...");
		boolean created = studentRepository.create(student);
		LOGGER.info("Students was successfully created" + student.toString());

		return created;
	}

	public int[] createAll(List<Student> studentsList) {

		LOGGER.debug("Student creating...");
		int[] createdAll = studentRepository.createAll(studentsList);
		LOGGER.info("All students were successfully created" + studentsList.toString());

		return createdAll;
	}

	public boolean addStudentToCourse(Student student, long courseId) {

		boolean isStudentNotOnCourse = !student.getCourses().contains(student);
		boolean isCourseExist = courseService.findById(courseId) != null;
		boolean isStudentExist = studentRepository.findById(student.getKey()) != null;
		boolean isStudentAddedToCourse = false;

		if (isStudentExist && isCourseExist && isStudentNotOnCourse) {
			Course course = courseService.findById(courseId);
			student.addCourse(course);

			studentRepository.update(student);
			isStudentAddedToCourse = true;
		}

		return isStudentAddedToCourse;
	}

	public boolean deleteStudentFromCourse(Student student, long courseId) {

		boolean studentDeletedFromCourse = false;
		boolean isCourseExist = courseService.findById(courseId) != null;
		boolean isStudentExist = studentRepository.findById(student.getKey()) != null;
		boolean studentOnCourse = student.getCourses().contains(student);

		if (isStudentExist && isCourseExist && studentOnCourse) {
			Course course = courseService.findById(courseId);
			student.deleteCourse(course);
			studentRepository.update(student);
			studentDeletedFromCourse = true;
		}

		return studentDeletedFromCourse;
	}

	public List<Student> findAll() {

		LOGGER.debug("All student findind...");
		List<Student> studentsList = studentRepository.findAll();
		LOGGER.info("All students were successfully found");

		return studentsList;
	}

	public Student findById(long key) {

		LOGGER.debug("Student finding by id");
		Student student = studentRepository.findById(key);
		LOGGER.info("Student was successfully found by id - " + key);

		return student;
	}

	public List<Student> findByName(String name) {

		LOGGER.debug("Student findind by name");
		List<Student> studentsList = studentRepository.findByName(name);
		LOGGER.info("Students were successfully found by name - " + name);

		return studentsList;
	}

	public boolean delete(Student student) {

		LOGGER.debug("Student deleting - " + student.toString());
		boolean deleted = studentRepository.delete(student);
		LOGGER.info("Student was successfully deleted with id - " + student.getKey());

		return deleted;
	}

	public boolean update(Student student) {

		LOGGER.debug("Student updating - " + student.toString());
		boolean updated = studentRepository.update(student);
		LOGGER.info("Student was successfully updated with id - " + student.getKey());

		return updated;
	}
}
