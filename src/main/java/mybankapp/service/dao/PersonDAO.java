package mybankapp.service.dao;

import mybankapp.domain.model.CurrencyAccount;
import mybankapp.domain.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    Optional<Person> find(UUID id);
    Optional<Person> findByName(String name);
    UUID create(Person person);
    List<CurrencyAccount> getPersonAccounts(UUID id);
    List<Person> findAll();
    void delete(UUID id);
}
