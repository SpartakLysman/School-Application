package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.GroupRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class GroupService {

	private PostgreSqlGroupDao postgreSqlGroupDao;

	private GroupRepository groupRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public GroupService() {

		this.postgreSqlGroupDao = new PostgreSqlGroupDao(groupRepository);

	}

	public boolean create(Group group) {

		LOGGER.debug("Group creating - " + group.toString());
		boolean created = postgreSqlGroupDao.create(group);
		LOGGER.info("Group was successfully created with id - " + group.getKey());

		return created;
	}

	public boolean createAll(List<Group> groupsList) {

		LOGGER.debug("All groups creating...");
		boolean createdAll = postgreSqlGroupDao.createAll(groupsList);
		LOGGER.info("All groups were successfully created " + groupsList.toString());

		return createdAll;
	}

	public boolean delete(Group group) {

		LOGGER.debug("Group deleting - " + group.toString());
		boolean deleted = postgreSqlGroupDao.deleteGroup(group);
		LOGGER.info("Group was successfully removed with id - " + group.getKey());

		return deleted;
	}

	public boolean update(Group group) {

		LOGGER.debug("Group updating - " + group.toString());
		boolean updated = postgreSqlGroupDao.update(group);
		LOGGER.info("Group was successfully updated with id - " + group.getKey());

		return updated;
	}

	public List<Group> findByTitle(String title) {

		LOGGER.debug("Group finding by title");
		List<Group> groupsList = postgreSqlGroupDao.findByTitle(title);
		LOGGER.info("Groups were successfully found by title - " + title);

		return groupsList;
	}

	public Optional<Group> findById(long key) {

		LOGGER.debug("Group findind by id");
		Optional<Group> group = postgreSqlGroupDao.findById(key);
		LOGGER.info("Group was successfully found by id - " + key);

		return group;
	}

	public List<Group> findAll() {

		LOGGER.debug("All groups findind...");
		List<Group> groupsList = postgreSqlGroupDao.findAll();
		LOGGER.info("All groups were successfully found ");

		return groupsList;
	}
}
