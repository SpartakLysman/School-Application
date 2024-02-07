package ua.com.foxminded.javaspring.SchoolApplication.serviceTest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.GroupService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@SpringBootTest(classes = { GroupService.class })
class GroupServiceTest {

	@MockBean
	PostgreSqlGroupDao postgreSqlGroupDao;

	@Autowired
	GroupService groupService;

	private List<Group> groupsList;

	private Group groupTest;
	private int[] size = new int[1];

	{
		Group gorupOne = new Group(1L, "1001");
		Group gorupTwo = new Group(2L, "1002");
		Group gorupTree = new Group(3L, "1003");
		Group gorupFour = new Group(4L, "1004");
		Group gorupFive = new Group(5L, "1005");
		Group gorupSix = new Group(6L, "1006");
		Group gorupSeven = new Group(7L, "1007");
		Group gorupEight = new Group(8L, "1008");
		Group gorupNine = new Group(9L, "1009");
		Group gorupTen = new Group(10L, "1010");

		groupsList = List.of(gorupOne, gorupTwo, gorupTree, gorupFour, gorupFive, gorupSix, gorupSeven, gorupEight,
				gorupNine, gorupTen);

		groupTest = groupsList.get(4);
		size[0] = 10;
	}

	@Test
	void createGroupTest() {
	
		when(postgreSqlGroupDao.create(any(Group.class))).thenReturn(true);

		Group newGroup = new Group(5L,  "1005");
		boolean isCreated = groupService.create(newGroup);
		
		assertTrue(isCreated);
		assertEquals(newGroup.getKey(), groupsList.get(4).getKey());
		assertEquals(newGroup.getTitle(), groupsList.get(4).getTitle());

		verify(postgreSqlGroupDao).create(any(Group.class));
	}

	@Test
	void createAllGroupsTest() {

		List<Group> groupslist = new ArrayList<>();

		Group groupNewOne = new Group(11L, "1011");
		Group groupNewTwo = new Group(12L, "1012");

		groupslist.add(groupNewOne);
		groupslist.add(groupNewTwo);

		when(postgreSqlGroupDao.createAll(groupslist)).thenReturn(true);

		List<Group> newGroupsList = List.of(groupNewOne, groupNewTwo);
		boolean create = groupService.createAll(newGroupsList);

		assertNotNull(create);
		assertEquals(newGroupsList.get(0).getTitle(), groupNewOne.getTitle());
		assertEquals(newGroupsList.get(1).getTitle(), groupNewTwo.getTitle());

		verify(postgreSqlGroupDao).createAll(newGroupsList);
	}

	@Test
	void deleteGroupTest() {
		
		when(postgreSqlGroupDao.deleteGroup(groupTest)).thenReturn(true);
		
		Group gorupOne = new Group(1L, "1001");
		Group gorupTwo = new Group(2L, "1002");
		Group gorupTree = new Group(3L, "1003");
		Group gorupFour = new Group(4L, "1004");
		
		Group gorupSix = new Group(6L, "1006");
		Group gorupSeven = new Group(7L, "1007");
		Group gorupEight = new Group(8L, "1008");
		Group gorupNine = new Group(9L, "1009");
		Group gorupTen = new Group(10L, "1010");

		
	List<Group> newGroupsList = List.of(gorupOne, gorupTwo, gorupTree, gorupFour, gorupSix, gorupSeven, gorupEight,
			gorupNine, gorupTen);
	
		boolean isDeleted = groupService.delete(groupTest);
		
		assertEquals(isDeleted, true);
		assertEquals(newGroupsList.size(), (groupsList.size() - 1));
			
		verify(postgreSqlGroupDao).deleteGroup(groupTest);
	}

	@Test
	void updateGroupTest() {

		Group groupForCheck = groupTest;

		when(postgreSqlGroupDao.update(groupTest)).thenReturn(true);

		groupTest = new Group(50L, "1050");
		boolean isUpdated = groupService.update(groupTest);

		assertNotEquals(groupForCheck, groupTest);

		verify(postgreSqlGroupDao).update(groupTest);
	}

	@Test
	void findGroupsByTitleTest() {
		 when(postgreSqlGroupDao.findByTitle(groupTest.getTitle())).thenReturn(List.of(groupTest));

	        List<Group> groupsListByTitle = groupService.findByTitle(groupTest.getTitle());

	        assertNotNull(groupsListByTitle);
	        assertEquals(groupsListByTitle.size(), 1);
	        assertEquals(groupsListByTitle.get(0).getTitle(), groupTest.getTitle());

	        verify(postgreSqlGroupDao).findByTitle(groupTest.getTitle());
	}

	@Test
	void findGroupByIdTest() {

		when(postgreSqlGroupDao.findById(groupTest.getKey())).thenReturn(Optional.of(groupsList.get(4)));

		Optional<Group> newGroup = groupService.findById(groupTest.getKey());
		
		assertEquals(newGroup.get().getKey(), groupTest.getKey());
		assertEquals(newGroup.get().getTitle(), groupTest.getTitle());

		verify(postgreSqlGroupDao).findById(groupTest.getKey());
	}

	@Test
	void findAllGroupsTest() {

		List<Group> groupsEntity = new ArrayList<>();

		for (int i = 1; i < groupsList.size(); i++) {
			groupsEntity.add(groupsList.get(i));
		}

		when(postgreSqlGroupDao.findAll()).thenReturn(groupsEntity);

		List<Group> newGroupsEntity = groupService.findAll();

		assertNotNull(groupsEntity);
		assertEquals(groupsEntity, newGroupsEntity);
		assertEquals(groupsEntity.get(0).getKey(), newGroupsEntity.get(0).getKey());

		verify(postgreSqlGroupDao).findAll();
	}
}
