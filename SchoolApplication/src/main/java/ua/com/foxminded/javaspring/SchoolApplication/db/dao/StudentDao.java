package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.User;

public interface StudentDao extends DAO<User, Long> {

	User login(String login, String password) throws DaoException;

	Student findById(Long key);

	boolean ifExistFindById(Long key);

	List<Student> findByName(String name);

}
