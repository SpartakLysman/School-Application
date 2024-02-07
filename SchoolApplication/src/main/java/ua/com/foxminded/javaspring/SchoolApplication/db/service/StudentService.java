package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.CourseRepository;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.StudentRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class StudentService {

	private Course course;

	@PersistenceContext
	private EntityManager entityManager;

	private PostgreSqlStudentDao postgreSqlStudentDao;

	private PostgreSqlCourseDao postgreSqlCourseDao;

	private StudentRepository studentRepository;

	private CourseRepository courseRepository;

	private static final String SQL_ADD_STUDENT_TO_COURSE = " insert into application.students_courses (student_id, course_id) "
			+ "	values (?, ?) ";
	private static final String SQL_DELETE_STUDENT_FROM_COURSE = " delete from application.students_courses where student_id = ? and course_id = ? ";

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public StudentService(StudentRepository studentRepository) {

		this.course = new Course();
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;

		this.postgreSqlCourseDao = new PostgreSqlCourseDao(courseRepository);
		this.postgreSqlStudentDao = new PostgreSqlStudentDao(studentRepository);
	}

	public boolean create(Student student) {

		LOGGER.debug("Student creating...");
		boolean created = postgreSqlStudentDao.create(student);
		LOGGER.info("Students was successfully created" + student.toString());

		return created;
	}

	public boolean createAll(List<Student> studentsList) {

		LOGGER.debug("Student creating...");
		boolean createdAll = postgreSqlStudentDao.createAll(studentsList);
		LOGGER.info("All students were successfully created" + studentsList.toString());

		return createdAll;
	}

	public boolean addStudentToCourse(Student student, long courseId) {

		boolean isStudentNotOnCourse = !course.getStudents().contains(student);
		boolean isCourseExist = postgreSqlStudentDao.findById(courseId) != null;
		boolean isStudentExist = postgreSqlStudentDao.findById(student.getKey()) != null;

		if (isStudentExist && isCourseExist && isStudentNotOnCourse) {
			Optional<Course> course = postgreSqlCourseDao.findById(courseId);
			course.get().addStudent(student);
			student.deleteCourse(course.get());
			studentRepository.update(student);

		} else {

			System.out.println("Some problems");
		}

		int updated = entityManager.createNativeQuery(SQL_ADD_STUDENT_TO_COURSE)
				.setParameter("students_id", student.getKey()).setParameter("course_id", courseId).executeUpdate();

		return updated > 0;
	}

	public boolean deleteStudentFromCourse(Student student, long courseId) {

		boolean isCourseExist = postgreSqlCourseDao.findById(courseId) != null;
		boolean isStudentExist = postgreSqlStudentDao.findById(student.getKey()) != null;
		boolean isStudentOnCourse = course.getStudents().contains(student);

		if (isStudentExist && isCourseExist && isStudentOnCourse) {
			Optional<Course> course = postgreSqlCourseDao.findById(courseId);
			course.get().deleteStudent(student);
			student.deleteCourse(course.get());
			studentRepository.update(student);

		} else {

			System.out.println("Some problems");
		}

		int deleted = entityManager.createNativeQuery(SQL_DELETE_STUDENT_FROM_COURSE)
				.setParameter("students_id", student.getKey()).setParameter("course_id", courseId).executeUpdate();

		return deleted > 0;
	}

	public boolean delete(Student student) {

		LOGGER.debug("Student deleting - " + student.toString());
		boolean deleted = postgreSqlStudentDao.deleteStudent(student);
		LOGGER.info("Student was successfully deleted with id - " + student.getKey());

		return deleted;
	}

	public boolean update(Student student) {

		LOGGER.debug("Student updating - " + student.toString());
		boolean updated = postgreSqlStudentDao.update(student);
		LOGGER.info("Student was successfully updated with id - " + student.getKey());

		return updated;
	}

	public List<Student> findByName(String name) {

		LOGGER.debug("Student findind by name");
		List<Student> studentsList = postgreSqlStudentDao.findByName(name);
		LOGGER.info("Students were successfully found by name - " + name);

		return studentsList;
	}

	public Optional<Student> findById(long key) {

		LOGGER.debug("Student finding by id");
		Optional<Student> student = postgreSqlStudentDao.findById(key);
		LOGGER.info("Student was successfully found by id - " + key);

		return student;
	}

	public List<Student> findAll() {

		LOGGER.debug("All student findind...");
		List<Student> studentsList = postgreSqlStudentDao.findAll();
		LOGGER.info("All students were successfully found");

		return studentsList;
	}
}
