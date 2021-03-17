package mybankapp.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import mybankapp.repository.CurrencyAccountRepository;
import mybankapp.repository.PersonRepository;
import mybankapp.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO{

    private final PersonRepository personRepository;

    private final CurrencyAccountRepository currencyAccountRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public Optional<Person> find(UUID id) {
        return personRepository.findById(id);
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
        Optional<Person> optionalPerson = personRepository.findById(id);
        List<CurrencyAccount> list = new ArrayList<>();
        if (optionalPerson.isPresent()) {
            list = optionalPerson.get().getAccounts();
        }
        return list;
    }

    @Override
    public Optional<CurrencyAccount> findAccount(long id) {
        return currencyAccountRepository.findById(id);
    }

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.saveAndFlush(transaction);
    }

    @Override
    public List<Transaction> getAccountTransactions(long id) {
        Optional<CurrencyAccount> optionalAccount = currencyAccountRepository.findById(id);
        List<Transaction> list = new ArrayList<>();
        if (optionalAccount.isPresent()) {
            list = optionalAccount.get().getTransactions();
        }
        return list;
    }

    @Override
    public Optional<Transaction> findTransaction(long id) {
        return transactionRepository.findById(id);
    }


}
