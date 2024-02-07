package ua.com.foxminded.javaspring.SchoolApplication.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student login(String login, String password) throws DaoException;

	@Modifying
	@Query("delete from application.students s where s = :students")
	boolean deleteStudent(@Param("students") Student student);

	List<Student> findByName(String name);

}