package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public interface DAO<E extends Entity<K>, K> {

	boolean create(E e);

	int[] createAll(List<E> e);

	boolean delete(E e);

	List<E> findAll();

	boolean update(E e);

}
