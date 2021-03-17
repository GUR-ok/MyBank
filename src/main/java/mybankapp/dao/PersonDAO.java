package mybankapp.dao;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    Optional<Person> find(UUID id);
    void create(Person person);

    void createAccount(CurrencyAccount account);
    List<CurrencyAccount> getPersonAccounts(UUID id);
    Optional<CurrencyAccount> findAccount(long id);

    public void createTransaction(Transaction transaction);
    List<Transaction> getAccountTransactions(long id);
    Optional<Transaction> findTransaction(long id);
}
