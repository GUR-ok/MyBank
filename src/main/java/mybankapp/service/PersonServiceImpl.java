package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.AccountDao;
import mybankapp.dao.PersonDAO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final AccountDao accountDao;

    @Override
    public void createPerson(Person person){
        personDAO.create(person);
    }

    @Override
    public Optional<Person> getPerson(UUID personUUID){
        return personDAO.find(personUUID);
    }

    @Override
    public List<Person> getAllPersons() {
        return personDAO.findAll();
    }

    @Override
    public void addAccount(CurrencyAccount account, UUID personUUID) {
        Optional<Person> optionalPerson = personDAO.find(personUUID);
        if (optionalPerson.isPresent()) {
            optionalPerson.get().getAccounts().add(account);
            account.setOwner(optionalPerson.get());
            accountDao.createAccount(account);
        }
    }
    @Override
    public List<CurrencyAccount> getAccounts(UUID personUUID) {
        return personDAO.getPersonAccounts(personUUID);
    }

    @Override
    public List<Transaction> getAccountTransactions(long accountId) {
        return accountDao.getAccountTransactions(accountId);
    }
}
