package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.AccountDAO;
import mybankapp.dao.PersonDAO;
import mybankapp.dao.TransactionDAO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

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
            accountDAO.createAccount(account);
        }
    }

    @Override
    public Optional<CurrencyAccount> getAccount(Long accounId) {
        return accountDAO.findAccount(accounId);
    }

    @Override
    public List<CurrencyAccount> getAllAccounts(UUID personUUID) {
        return personDAO.getPersonAccounts(personUUID);
    }

    @Override
    public List<Transaction> getAccountTransactions(long accountId) {
        return accountDAO.getAccountTransactions(accountId);
    }

    @Override
    public void deletePerson(UUID personUUID) {
        personDAO.delete(personUUID);
    }

    @Override
    public void deleteAccount(long accountId) {
        accountDAO.delete(accountId);
    }

    @Override
    @Transactional
    public void addTransaction(Transaction transaction, Long accountId) {
        Optional<CurrencyAccount> account = accountDAO.findAccount(accountId);
        if (account.isPresent()){
            account.get().getTransactions().add(transaction);
            transaction.setAccount(account.get());
            Double amount = account.get().getBalance();
            amount += transaction.getAmount();
            account.get().setBalance(amount);
            transactionDAO.createTransaction(transaction);
        }
    }
}
