package dao;

import dbconnection.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcDepartmentDao implements DepartmentDao {

    @Autowired
    private DatabaseConnection databaseConnection;

    private String basicQuery = "from " +
            "((lectors as l inner join lectors_departments as ld on l.id = ld.lector_id) " +
            "inner join departments as d on d.dp_id = ld.department_id)";

    public String getHeadOfDepartment(String departmentName) {
        try {
            Statement statement = databaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select head_of_department from departments " +
                    "where dp_name = '" + departmentName + "'");
            resultSet.next();
            return resultSet.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAssistantCount(String departmentName) {
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT COUNT(*) " + basicQuery + " WHERE d.dp_name = ? AND l.degree = 'assistant'");
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getAssociateProfessorsCount(String departmentName) {
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT COUNT(*) " + basicQuery + " WHERE d.dp_name = ? AND l.degree = 'associate professor'");
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getProfessorsCount(String departmentName) {
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "select COUNT(*) " + basicQuery + " WHERE d.dp_name = ? AND l.degree = 'professor'");
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getAverageSalary(String departmentName) {
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT AVG(l.salary) " + basicQuery + " WHERE d.dp_name = ?");
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getEmployeesCount(String departmentName) {
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT COUNT(*) " + basicQuery + " WHERE d.dp_name = ?");
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<String> search(String name) {
        ArrayList<String> result = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT NAME FROM lectors WHERE name LIKE ?");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
