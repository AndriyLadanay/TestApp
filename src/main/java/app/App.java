package app;

import services.AnswerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App {


    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            AnswerService service = context.getBean(AnswerService.class);

            System.out.println("To finish program execution enter word \"quit\" and press enter button");
            while (true) {
                String question = reader.readLine();
                if(question.equals("quit")) break;
                service.printAnswer(question);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
