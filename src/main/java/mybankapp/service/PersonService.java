package mybankapp.service;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    void createPerson(Person person);
    Optional<Person> getPerson(UUID personUUID);
    List<Person> getAllPersons();
    void addAccount(CurrencyAccount account, UUID personUUID);
    Optional<CurrencyAccount> getAccount(long accounId);
    List<CurrencyAccount> getAllAccounts(UUID personUUID);
    List<Transaction> getAccountTransactions(long accountId);
    void deletePerson(UUID personUUID);
    void deleteAccount(long accountId);
    void addTransaction(Transaction transaction, long accountId);
}
