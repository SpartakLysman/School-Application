package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.GroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@Repository
@Transactional
public class PostgreSqlGroupDao implements GroupDao {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String SQL_CREATE_GROUP = " insert into application.groups (group_id, title) "
			+ " values (?, ?) ";
	private static final String SQL_DELETE_GROUP = " delete from application.groups " + " where group_id = ? ";
	private static final String SQL_UPDATE_GROUP = " update application.groups set title = ? " + " where group_id = ? ";
	private static final String SQL_FIND_GROUP_BY_ID = " select * from application.groups " + " where group_id = ? ";
	private static final String SQL_FIND_GROUP_BY_TITLE = " select * from application.groups " + " where title = ? ";
	private static final String SQL_FIND_ALL = " select * from application.groups";

	@Autowired
	public PostgreSqlGroupDao(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public boolean create(Group group) {

		try {
			entityManager.persist(group);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean createAll(List<Group> groupsList) {

		try {
			for (Group group : groupsList) {
				entityManager.persist(group);
			}
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean update(Group group) {

		try {
			entityManager.merge(group);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean delete(Group group) {

		try {

			Group group1 = entityManager.find(Group.class, group.getKey);
			if (group1 != null) {
				entityManager.remove(group1);
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
		return entityManager.find(Group.class, key) != null;
	}

	@Override
	public List<Group> findByTitle(String title) {
		return entityManager.createQuery(SQL_FIND_GROUP_BY_TITLE, Group.class).setParameter("title", title)
				.getResultList();
	}

	@Override
	public Group findById(Long key) {
		return entityManager.find(Group.class, key);
	}

	public List<Group> findAll() {
		return entityManager.createQuery(SQL_FIND_ALL, Group.class).getResultList();
	}
}
