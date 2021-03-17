package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.PersonDAO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonDAO personDAO;

    public void createPerson(Person person){
        personDAO.create(person);
    }

    public Optional<Person> getPerson(UUID id){
        return personDAO.find(id);
    }

    public void addAccount(CurrencyAccount account, UUID personUUID) {
        Optional<Person> optionalPerson = personDAO.find(personUUID);
        if (optionalPerson.isPresent()) {
            optionalPerson.get().getAccounts().add(account);
            account.setOwner(optionalPerson.get());
            personDAO.createAccount(account);
        }
    }

    public List<CurrencyAccount> getAccounts(UUID personUUID) {
        return personDAO.getPersonAccounts(personUUID);
    }
}
