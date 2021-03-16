package mybankapp;

import mybankapp.model.Person;
import mybankapp.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyBankApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyBankApplication.class, args);
        PersonService service = context.getBean(PersonService.class);
        Person pers1 = context.getBean(Person.class);
        Person pers2 = context.getBean(Person.class);
        service.createPerson(pers1);
        service.createPerson(pers2);
        System.out.println(service.getPerson(2).getName()+" found");
    }
}
