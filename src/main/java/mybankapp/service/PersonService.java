package mybankapp.service;

import mybankapp.dao.PersonDAO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    public void createPerson(Person person){
        personDAO.create(person);
    }

    public Person getPerson(UUID id){
        return personDAO.find(id);
    }

    public void addAccount(CurrencyAccount account, UUID personUUID) {
        Person person = personDAO.find(personUUID);
        person.getAccounts().add(account);
        account.setOwner(person);
        personDAO.createAccount(account);
    }
}
