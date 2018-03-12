package dao;

import java.util.List;

public interface DepartmentDao {

    String getHeadOfDepartment(String departmentName);

    int getAssistantCount(String departmentName);

    int getAssociateProfessorsCount(String departmentName);

    int getProfessorsCount(String departmentName);

    double getAverageSalary(String departmentName);

    int getEmployeesCount(String departmentName);

    List<String> search(String name);
}
