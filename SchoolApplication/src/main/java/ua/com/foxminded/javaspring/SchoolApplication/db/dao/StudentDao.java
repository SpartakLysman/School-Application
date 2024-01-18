package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public interface StudentDao extends DAO<Student, Long> {

	Student login(String login, String password) throws DaoException;

	Student findById(Long key);

	List<Student> findByName(String name);

	boolean ifExistFindById(Long key);
}
