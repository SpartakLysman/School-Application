package ua.com.foxminded.javaspring.SchoolApplication.serviceTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@SpringBootTest(classes = { StudentService.class })
class StudentServiceTest {

	@MockBean
	PostgreSqlStudentDao postgreSqlStudentDao;

	@Autowired
	StudentService studentService;

	private List<Student> studentsList;
	private Student studentTest;
	private int[] size = new int[1];

	{
		Student studentOne = new Student(1L, 1, "Alexey", "Bionic", "StudentOne", "5r!A54");
		Student studentTwo = new Student(2L, 1, "Dmitry", "Shwez", "StudentTwo", "f27&jvh");
		Student studentThree = new Student(3L, 5, "Maxim", "Makilok", "StudentThree", "dfanS2e");
		Student studentFour = new Student(4L, 4, "Dmitry", "Dolib", "StudentFoure", "Dfab29&");
		Student studentFive = new Student(5L, 5, "Potap", "Kilot", "StudentFive", "DdH3&hs");
		Student studentSix = new Student(6L, 5, "Pavel", "Dertinovskiy", "StudentSix", "@S!e4B");
		Student studentSeven = new Student(7L, 3, "Artem", "Makyk", "StudentSeven", "Vr432!a");
		Student studentEight = new Student(8L, 6, "Vladislav", "Uzumbiev", "StudentEight", "Uzum3!$4");
		Student studentNine = new Student(9L, 1, "Makar", "Kiminok", "StudentNine", "ffA2er1");
		Student studentTen = new Student(10L, 1, "Gleb", "Delokyz", "StudentTen", "12S!kk");
		Student studentEleven = new Student(11L, 1, "Mark", "Dolyn", "StudentEleven", "*37FFS3");
		Student studentTwelve = new Student(12L, 1, "Yuriy", "Dernisholz", "StudentTwelve", "FF2sa!!");
		Student studenThirteen = new Student(13L, 2, "Ivan", "Fomin", "StudenThirteen", "Haapd3");
		Student studentFourteen = new Student(14L, 2, "Ivan", "Abroktin", "StudentFourteen", "Dc3cdj#");
		Student studentFivteen = new Student(15L, 6, "Artem", "Ernishev", "StudentFivteen", "adi&64L");
		Student studentSixteen = new Student(16L, 4, "Makhail", "Goodman", "StudentSixteen", "KK37aY");
		Student studentSeventeen = new Student(17L, 3, "Yaroslav", "Wertex", "StudentSeventeen", "YY28&a");
		Student studentEighteen = new Student(18L, 5, "Bpogdan", "Dertalok", "StudentEighteen", "2p37H#3");
		Student studentNineteen = new Student(19L, 5, "Michail", "Frukinchok", "StudentNineteen", "m81J!&");
		Student studentTwenty = new Student(20L, 4, "Nikolay", "Sertilatov", "StudentTwenty", "D72xQ&");

		studentsList = List.of(studentOne, studentTwo, studentThree, studentFour, studentFive, studentSix, studentSeven,
				studentEight, studentNine, studentTen, studentEleven, studentTwelve, studenThirteen, studentFourteen,
				studentFivteen, studentSixteen, studentSeventeen, studentEighteen, studentNineteen, studentTwenty);

		studentTest = studentsList.get(4);
		size[0] = 10;
	}

	@Test
	void createStudentTest() {
		
		when(postgreSqlStudentDao.create(any(Student.class))).thenReturn(true);

		Student newStudent = new Student(5L, 5, "Nekolay", "Ivatko", "StudentFive", "fH2*fh");
		boolean isCreated = studentService.create(newStudent);
		
		assertTrue(isCreated);
		assertEquals(newStudent.getName(), newStudent.getName());
		assertEquals(newStudent.getSurname(), newStudent.getSurname());

		verify(postgreSqlStudentDao).create(any(Student.class));
	}

	@Test
	void createAllStudentsTest() {

		List<Student> usersStudent = new ArrayList<>();

		Student studentNewOne = new Student(21L, 6, "Vasiliy", "Manumanian", "StudentTwentyFirst", "Gj4fss&");
		Student studentNewTwo = new Student(22L, 4, "Ignat", "Fidokib", "StudentTwentySecond", "LL46@!S");

		usersStudent.add(studentNewOne);
		usersStudent.add(studentNewTwo);

		// int[] sizeNew = new int[2];

		when(postgreSqlStudentDao.createAll(usersStudent)).thenReturn(true);

		List<Student> newStudentsList = List.of(studentNewOne, studentNewTwo);
		boolean create = studentService.createAll(newStudentsList);

		assertNotNull(create);
		assertEquals(newStudentsList.get(0).getName(), studentNewOne.getName());
		assertEquals(newStudentsList.get(1).getName(), studentNewTwo.getName());

		verify(postgreSqlStudentDao).createAll(newStudentsList);
	}

	@Test
	void deleteStudentTest() {
		int listSize = studentsList.size();
		when(postgreSqlStudentDao.delete(studentTest)).thenReturn(true);

		boolean isDeleted = studentService.delete(studentTest);

		assertEquals(isDeleted, true);
		assertEquals((listSize - 1), studentsList.size());

		verify(postgreSqlStudentDao).delete(studentTest);
	}

	@Test
	void updateStudentTest() {

		Student studentForCheck = studentTest;

		when(postgreSqlStudentDao.update(studentTest)).thenReturn(true);

		studentTest = new Student(50L, 5, "Anton", "Gorodnuk", "StudentFifty", "gHs29*&");

		boolean isUpdated = studentService.update(studentTest);

		assertNotEquals(studentForCheck, studentTest);
		verify(postgreSqlStudentDao).update(studentTest);
	}

	@Test
	void findStudentsByNameTest() {
		
		 when(postgreSqlStudentDao.findByName(studentTest.getName())).thenReturn(List.of(studentTest));

	        List<Student> studentsListByTitle = studentService.findByName(studentTest.getName());

	        assertNotNull(studentsListByTitle);
	        assertEquals(studentsListByTitle.size(), 1);
	        assertEquals(studentsListByTitle.get(0).getName(), studentTest.getName());

	        verify(postgreSqlStudentDao).findByName(studentTest.getName());
	}

	@Test
	void findStudentByIdTest() {
		
		when(postgreSqlStudentDao.findById(5L)).thenReturn(studentTest);

		Student newStudent = studentService.findById(5L);
		
		assertEquals(newStudent.getKey(), studentTest.getKey());
		assertEquals(newStudent.getName(), studentTest.getName());

		verify(postgreSqlStudentDao).findById(5L);
	}

	@Test
	void findAllStudentsTest() {

		List<Student> studentsEntity = new ArrayList<>();

		for (int i = 1; i < studentsList.size(); i++) {
			studentsEntity.add(studentsList.get(i));
		}

		Student studentFirst = studentsEntity.get(0);

		when(postgreSqlStudentDao.findAll()).thenReturn(studentsEntity);

		List<Student> newStudentsEntity = studentService.findAll();

		assertNotNull(studentsEntity);
		assertEquals(studentsEntity, newStudentsEntity);
		assertEquals(studentsEntity.get(0).getKey(), newStudentsEntity.get(0).getKey());

		verify(postgreSqlStudentDao).findAll();
	}
}
