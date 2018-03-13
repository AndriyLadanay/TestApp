package services;

import dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    protected DepartmentDao dao;

    public String getAnswer(String question) {
        if (question.startsWith("Who is head of department")) {
            String dpName = question.split(" ")[5];
            return "Head of " + dpName + " department is " + dao.getHeadOfDepartment(dpName);
        } else if(question.startsWith("Show") && question.endsWith("statistics")) {
            String dpName = question.split(" ")[1];
            StringBuilder builder = new StringBuilder();
            builder.append("assistants - " + dao.getAssistantCount(dpName) + System.lineSeparator());
            builder.append("associate professors - " + dao.getAssociateProfessorsCount(dpName) + System.lineSeparator());
            builder.append("professors - " + dao.getProfessorsCount(dpName));
            return builder.toString();
        } else if(question.startsWith("Show the average salary for department")) {
            String dpName = question.split(" ")[6];
            return "The average salary of " + dpName + " is " + dao.getAverageSalary(dpName);
        } else if(question.startsWith("Show count of employee for")) {
            String dpName = question.split(" ")[5];
            return dao.getEmployeesCount(dpName) + "";
        } else if(question.startsWith("Global search by")) {
            String template = question.split(" ")[3];
            List<String> results = dao.search(template);
            StringBuilder builder = new StringBuilder();
            for (String result: results ) {
                builder.append(result);
                builder.append(", ");
            }
            builder.deleteCharAt(builder.lastIndexOf(", "));
            builder.deleteCharAt(builder.lastIndexOf(" "));
            return builder.toString();
        } else { return "I am not so smart to answer this"; }
    }
}
