package mybankapp.dao;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.repository.CurrencyAccountRepository;
import mybankapp.repository.PersonRepository;
import mybankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CurrencyAccountRepository currencyAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Person find(UUID id) {
        return (Person) personRepository.findById(id).get();
    }

    @Override
    public void create(Person person) {
        personRepository.saveAndFlush(person);
    }

    @Override
    public void createAccount(CurrencyAccount account) {
        currencyAccountRepository.saveAndFlush(account);
    }

    @Override
    public List<CurrencyAccount> getPersonAccounts(UUID id) {
        Person person = (Person) personRepository.findById(id).get();
        return person.getAccounts();
    }
}
