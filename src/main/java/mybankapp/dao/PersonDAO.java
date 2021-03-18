package mybankapp.dao;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    Optional<Person> find(UUID id);
    void create(Person person);
    List<CurrencyAccount> getPersonAccounts(UUID id);
    List<Person> findAll();
    void delete(UUID id);
}
