package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.GroupRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@Repository
@Transactional
public class PostgreSqlGroupDao {

	private final GroupRepository groupRepository;

	@Autowired
	public PostgreSqlGroupDao(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public boolean create(Group group) {
		try {
			groupRepository.save(group);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean createAll(List<Group> groupsList) {
		try {
			groupRepository.saveAll(groupsList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteGroup(Group group) {
		try {
			groupRepository.delete(group);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Group group) {
		try {
			groupRepository.save(group);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Group> findByTitle(String title) {
		return groupRepository.findByTitle(title);
	}

	public Optional<Group> findById(Long key) {
		return groupRepository.findById(key);
	}

	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	public boolean ifExistFindById(Long key) {
		return groupRepository.existsById(key);
	}
}
