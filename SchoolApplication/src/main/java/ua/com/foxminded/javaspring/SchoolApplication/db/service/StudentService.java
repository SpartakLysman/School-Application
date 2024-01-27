package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class StudentService {

	private Course course;
	private JdbcTemplate jdbcTemplate;
	private CourseService courseService;
	private PostgreSqlStudentDao studentRepository;

	private static final String SQL_ADD_STUDENT_TO_COURSE = " insert into application.students_courses (student_id, course_id) "
			+ "	values (?, ?) ";
	private static final String SQL_DELETE_STUDENT_FROM_COURSE = " delete from application.students_courses where student_id = ? and course_id = ? ";

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public StudentService(JdbcTemplate jdbcTemplate) {

		this.course = new Course();
		this.jdbcTemplate = jdbcTemplate;
		this.courseService = new CourseService(jdbcTemplate);
		this.studentRepository = new PostgreSqlStudentDao(jdbcTemplate);

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

		boolean isStudentNotOnCourse = !course.getStudents().contains(student);
		boolean isCourseExist = courseService.findById(courseId) != null;
		boolean isStudentExist = studentRepository.findById(student.getKey()) != null;

		if (isStudentExist && isCourseExist && isStudentNotOnCourse) {
			Course course = courseService.findById(courseId);
			course.addStudent(student);
			student.addCourse(course);
			studentRepository.update(student);

		} else {

			System.out.println("Some problems");
		}

		return jdbcTemplate.update(SQL_ADD_STUDENT_TO_COURSE, student.getKey(), courseId) > 0;
	}

	public boolean deleteStudentFromCourse(Student student, long courseId) {

		boolean isCourseExist = courseService.findById(courseId) != null;
		boolean isStudentExist = studentRepository.findById(student.getKey()) != null;
		boolean isStudentOnCourse = course.getStudents().contains(student);

		if (isStudentExist && isCourseExist && isStudentOnCourse) {
			Course course = courseService.findById(courseId);
			course.deleteStudent(student);
			student.deleteCourse(course);
			studentRepository.update(student);

		} else {

			System.out.println("Some problems");
		}

		return jdbcTemplate.update(SQL_DELETE_STUDENT_FROM_COURSE, student.getKey(), courseId) > 0;
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
