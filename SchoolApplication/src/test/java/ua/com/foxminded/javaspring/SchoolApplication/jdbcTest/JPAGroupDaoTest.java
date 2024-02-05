package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@SpringBootTest(classes = { EntityManager.class, PostgreSqlGroupDao.class, EntityManagerFactory.class })
class JPAGroupDaoTest {

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private PostgreSqlGroupDao postgreSqlGroupDao;

	private List<Group> groupsList;
	private Group groupFirst;
	private Group groupTest;

	{
		groupsList = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			Group group = new Group();
			group.setKey((long) i);
			group.setTitle(group.getTitle());

			groupsList.add(group);
		}
		groupFirst = groupsList.get(0);
		groupTest = new Group();
		groupTest.setKey(5L);

	}

	@BeforeEach
	void setUp() {
		postgreSqlGroupDao = new PostgreSqlGroupDao(entityManager);
	}

	@Test
	void testCreateGroup() {

		PostgreSqlGroupDao postgreSqlGroupDao = mock(PostgreSqlGroupDao.class);

		groupTest.setKey(6L);
		groupTest.setTitle("Sixth");

		when(postgreSqlGroupDao.create(groupTest)).thenReturn(true);

		boolean isCreated = postgreSqlGroupDao.create(groupTest);

		assertTrue(isCreated);

		verify(postgreSqlGroupDao).create(groupTest);

		postgreSqlGroupDao.delete(groupTest);

	}

	@Test
	void testCreateAllGroups() {

		PostgreSqlGroupDao postgreSqlGroupDao = mock(PostgreSqlGroupDao.class);

		List<Group> groupslist = new ArrayList<>();

		Group groupNewOne = new Group(11L, "New Group One");
		Group groupNewTwo = new Group(12L, "New Group Two");

		groupslist.add(groupNewOne);
		groupslist.add(groupNewTwo);

		when(postgreSqlGroupDao.createAll(groupslist)).thenReturn(true);

		List<Group> newGroupsList = List.of(groupNewOne, groupNewTwo);
		boolean isCreated = postgreSqlGroupDao.createAll(newGroupsList);

		assertEquals(newGroupsList.get(0).getTitle(), groupNewOne.getTitle());
		assertEquals(newGroupsList.get(1).getTitle(), groupNewTwo.getTitle());

		verify(postgreSqlGroupDao).createAll(newGroupsList);
	}

	@Test
	void testDeleteGroup() {

		PostgreSqlGroupDao postgreSqlGroupDao = mock(PostgreSqlGroupDao.class);

		groupTest.setTitle("First");

		when(postgreSqlGroupDao.delete(groupTest)).thenReturn(true);

		postgreSqlGroupDao.delete(groupTest);

		assertTrue(postgreSqlGroupDao.delete(groupTest));
	}

	@Test
	void testUpdateGroup() {

		PostgreSqlGroupDao postgreSqlGroupDao = mock(PostgreSqlGroupDao.class);

		groupTest.setKey(1L);
		groupTest.setTitle("First");

		when(postgreSqlGroupDao.update(groupTest)).thenReturn(true);

		assertTrue(postgreSqlGroupDao.update(groupTest));

		verify(postgreSqlGroupDao, Mockito.times(1)).update(groupTest);
	}

	@Test
	void testFindByTitle() {

		Group group1 = new Group();
		group1.setTitle("First");

		Group group2 = new Group();
		group2.setTitle("First");

		List<Group> expectedGroups = Arrays.asList(group1, group2);

		TypedQuery<Group> queryMock = mock(TypedQuery.class);
		when(entityManager.createQuery(anyString(), eq(Group.class))).thenReturn(queryMock);
		when(queryMock.setParameter(eq("title"), eq("First"))).thenReturn(queryMock);
		when(queryMock.getResultList()).thenReturn(expectedGroups);

		List<Group> resultGroups = postgreSqlGroupDao.findByTitle("First");

		assertEquals(expectedGroups, resultGroups);

		verify(entityManager).createQuery(anyString(), eq(Group.class));
		verify(queryMock).setParameter(eq("title"), eq("First"));
		verify(queryMock).getResultList();
	}

	@Test
	void testFindById() {

		Group expected = new Group();
		expected.setKey(1L);
		Mockito.when(entityManager.find(Mockito.eq(Group.class), Mockito.any())).thenReturn(expected);
		Group actual = postgreSqlGroupDao.findById(1L);
		assertEquals(expected, actual);

	}

	@Test
	void testFindAll() {

		when(postgreSqlGroupDao.findAll()).thenReturn(groupsList);

	    List<Group> result = postgreSqlGroupDao.findAll();

	    assertEquals(groupsList, result);

	    verify(postgreSqlGroupDao, Mockito.times(1)).findAll();   
	}

	@Test
	void testCheckIfExistByID() {

		PostgreSqlGroupDao postgreSqlGroupDao = mock(PostgreSqlGroupDao.class);

		when(postgreSqlGroupDao.ifExistFindById(3L)).thenReturn(true);

		boolean isExist = postgreSqlGroupDao.ifExistFindById(3L);

		assertTrue(isExist);

		verify(postgreSqlGroupDao, Mockito.times(1)).ifExistFindById(3L);
	}
}
