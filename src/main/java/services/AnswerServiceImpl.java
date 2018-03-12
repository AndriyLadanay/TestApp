package services;

import dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private DepartmentDao dao;

    public void printAnswer(String question) {
        if (question.startsWith("Who is head of department")) {
            String dpName = question.split(" ")[5];
            System.out.println("Head of " + dpName + " department is " + dao.getHeadOfDepartment(dpName));
        }
        else if(question.startsWith("Show") && question.endsWith("statistics")) {
            String dpName = question.split(" ")[1];
            System.out.println("assistants - " + dao.getAssistantCount(dpName));
            System.out.println("associate professors - " + dao.getAssociateProfessorsCount(dpName));
            System.out.println("professors - " + dao.getProfessorsCount(dpName));
        }
        else if(question.startsWith("Show the average salary for department")) {
            String dpName = question.split(" ")[6];
            System.out.println("The average salary of " + dpName + " is " + dao.getAverageSalary(dpName));
        }
        else if(question.startsWith("Show count of employee for")) {
            String dpName = question.split(" ")[5];
            System.out.println(dao.getEmployeesCount(dpName));
        }
        else if(question.startsWith("Global search by")) {
            String template = question.split(" ")[3];
            List<String> results = dao.search(template);
            for (String result: results ) {
                System.out.print(result + ", ");
            }
        }
        else System.out.println("I am not so smart to answer this");
    }
}
