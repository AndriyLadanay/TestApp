package dao;

import dbconnection.DatabaseConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.junit.Assert.*;

/* Test database consists of 3 tables:
- lectors (id int primary key identity, name varchar , degree varchar, salary int)
- departments (dp_id int primary key, dp_name varchar, head_of_department varchar)
- lectors_departments (lector_id int references lectors(id), department_id int references departments(dp_id),
  primary key (lector_id, department_id))
  Backup file of database you can find in folder "resources" (test.bak)
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class DaoTest {

    @Autowired
    private DatabaseConnection connection;

    @Autowired
    private DepartmentDao dao;

    @Test
    public void testGetHeadOfDepartment() {
        assertEquals("Stepan Petrov", dao.getHeadOfDepartment("IT"));
    }

    @Test
    public void testDepartmentStatistics() {
        assertEquals(0, dao.getAssistantCount("IT"));
        assertEquals(1, dao.getAssociateProfessorsCount("IT"));
        assertEquals(0, dao.getProfessorsCount("IT"));
    }

    @Test
    public void testAverageSalary() {
        assertEquals("Comparing salaries", 6000d, dao.getAverageSalary("IT"), 0);
    }

    @Test
    public void testCountOfEmployees() {
        assertEquals(1, dao.getEmployeesCount("IT"));
    }
    public void testSearch() {
        List<String> result = dao.search("Fed");
        assertEquals("Sergiy Fedchuk", result.get(0));
        assertEquals("Natalya Fedchuk", result.get(1));
    }
}
