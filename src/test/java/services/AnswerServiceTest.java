package services;

import dao.DepartmentDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AnswerServiceTest {

    @Mock
    private DepartmentDao dao;

    @InjectMocks
    private AnswerServiceImpl service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHeadOfDepartment() {
        when(dao.getHeadOfDepartment("IT")).thenReturn("Kirill Petrov");
        assertEquals("Head of IT department is Kirill Petrov",
                service.getAnswer("Who is head of department IT"));
        verify(dao).getHeadOfDepartment("IT");
    }

    @Test
    public void testGetDepartmentStatistics() {
        when(dao.getAssistantCount("IT")).thenReturn(3);
        when(dao.getAssociateProfessorsCount("IT")).thenReturn(2);
        when(dao.getProfessorsCount("IT")).thenReturn(1);
        assertEquals("assistants - 3" + System.lineSeparator() +
                "associate professors - 2" + System.lineSeparator() +
                "professors - 1", service.getAnswer("Show IT statistics"));
        verify(dao).getAssistantCount("IT");
        verify(dao).getAssociateProfessorsCount("IT");
        verify(dao).getProfessorsCount("IT");
    }

    @Test
    public void testGetAverageSalary() {
        when(dao.getAverageSalary("IT")).thenReturn(2000d);
        assertEquals("The average salary of IT is 2000.0", service.getAnswer(
                "Show the average salary for department IT"));
        verify(dao).getAverageSalary("IT");
    }

    @Test
    public void testCountOfEmployee() {
        when(dao.getEmployeesCount("IT")).thenReturn(10);
        assertEquals("10", service.getAnswer("Show count of employee for IT"));
        verify(dao).getEmployeesCount("IT");
    }

    @Test
    public void testSearch() {
        when(dao.search("Fed")).thenReturn(Arrays.asList(new String[]{"Sergiy Fedchuk", "Natalya Fedchuk"}));
        assertEquals("Sergiy Fedchuk, Natalya Fedchuk", service.getAnswer("Global search by Fed"));
        verify(dao).search("Fed");
    }
}
