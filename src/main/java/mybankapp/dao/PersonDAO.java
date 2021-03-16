package mybankapp.dao;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDAO {

    Person find(UUID id);
    void create(Person person);

    void createAccount(CurrencyAccount account);
    List<CurrencyAccount> getPersonAccounts(UUID id);
}
