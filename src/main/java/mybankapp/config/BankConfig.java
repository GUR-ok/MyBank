package mybankapp.config;
import mybankapp.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;
import java.util.UUID;

@Configuration
public class BankConfig {

    @Bean
    @Scope("prototype")
    public Person getPerson() {
        Person person = new Person();
        Random random = new Random();
        person.setName("Name"+random.nextInt(100));
     // person.setUuid(UUID.randomUUID().toString());
        return person;
    }
}
