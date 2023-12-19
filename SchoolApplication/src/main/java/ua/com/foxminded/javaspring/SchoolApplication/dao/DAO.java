package ua.com.foxminded.javaspring.SchoolApplication.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;

public interface DAO<E extends Entity<K>, K> {

	E create(E e);

	E delete(E e);

	List<E> findAll();

	E update(E e);

}
