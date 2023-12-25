package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;

public interface DAO<E extends Entity<K>, K> {

	boolean create(E e);

	boolean delete(E e);

	List<Entity> findAll();

	boolean update(E e);

}
