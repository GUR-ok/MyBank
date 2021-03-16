package mybankapp.config;
import mybankapp.model.Currency;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
public class BankConfig {

    @Bean
    @Scope("prototype")
    public Person getPerson() {
        Person person = new Person();
        Random random = new Random();
        person.setName("Name"+random.nextInt(100));
        return person;
    }

    @Bean
    @Scope("prototype")
    public CurrencyAccount getAccount() {
        CurrencyAccount account = new CurrencyAccount();
        account.setCurrency(Currency.USD);
        account.setBalance(200);
        return account;
    }
}
