package ua.com.foxminded.javaspring.SchoolApplication.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student login(String login, String password) throws DaoException;

	boolean create(Student student);

	boolean createAll(List<Student> e);

	@Modifying
	@Query("delete from application.students s where s = :students")
	boolean deleteStudent(@Param("students") Student student);

	boolean update(Student student);

	Optional<Student> findById(Long key);

	List<Student> findByName(String name);

	List<Student> findAll();

	boolean ifExistFindById(Long key);
}