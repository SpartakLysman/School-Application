package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {EntityManager.class, PostgreSqlCourseDao.class, EntityManagerFactory.class})
class JPACourseDaoTest {

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private PostgreSqlCourseDao postgreSqlCourseDao;

    @BeforeEach
    void setUp() {
        postgreSqlCourseDao = new PostgreSqlCourseDao(entityManager);
    }

    @Test
    void findById() {
        Course expected = new Course();
        expected.setKey(1L);

        Mockito.when(entityManager.find(Mockito.eq(Course.class), Mockito.any())).thenReturn(expected);

        Course actual = postgreSqlCourseDao.findById(1L);

        assertEquals(expected, actual);
    }

}
