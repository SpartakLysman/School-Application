package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.StudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.StudentMapper;

@Repository
@Transactional
public class PostgreSqlStudentDao implements StudentDao {

	private static Logger log = Logger.getLogger(PostgreSqlStudentDao.class.getName());
	private DataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	private static final String SQL_CREATE_STUDENT = "insert into application.students (students_id, group_id, name, surname, login, password)"
			+ "	values (?, ?, ?, ?, ?, ?) ";

	private static final String SQL_DELETE_STUDENT = "delete from application.students where students_id = ? ";
	private static final String SQL_UPDATE_STUDENT = "update application.students set group_id = ?, name = ?, surname = ?, login = ?, password = ? "
			+ " where students_id = ? ";
	private static final String SQL_FIND_STUDENT_BY_ID = "select * from application.students "
			+ " where students_id = ? ";
	private static final String SQL_FIND_STUDENT_BY_NAME = " select * from application.students " + " where name = ? ";
	private static final String SQL_FIND_ALL = " select * from application.students ";

	@Autowired
	public PostgreSqlStudentDao(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public Student login(String login, String password) throws DaoException {
		log.config("Looking for customer with the login: " + login + " and password: " + password);

		String sql = "SELECT * FROM students WHERE login = ? AND password = ?";

		return entityManager.createQuery(sql, new Student[] { login, password }, new StudentMapper()).stream()
				.findFirst().orElse(null);
	}

	public boolean create(Student student) {
		try {
			entityManager.persist(student);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean createAll(List<Student> studentsList) {

		try {
			for (Student student : studentsList) {
				entityManager.persist(student);
			}
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean update(Student student) {

		try {
			entityManager.merge(student);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean delete(Student student) {

		try {
			Student student1 = entityManager.find(Course.class, student.getKey);
			if (student1 != null) {
				entityManager.remove(student1);
				return true;

			} else {

				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean ifExistFindById(Long key) {
		return entityManager.find(Student.class, key) != null;
	}

	@Override
	public List<Student> findByName(String name) {
		return entityManager.createQuery(SQL_FIND_STUDENT_BY_NAME, Student.class).setParameter("name", name)
				.getResultList();
	}

	@Override
	public Student findById(Long key) {

		return entityManager.find(Student.class, key);
	}

	public List<Student> findAll() {
		return entityManager.createQuery(SQL_FIND_ALL, Student.class).getResultList();
	}

	public TypedQuery<Integer> getCountOfStudents() {
		return entityManager.createQuery("SELECT COUNT(*) FROM students", Integer.class);

	}
}
