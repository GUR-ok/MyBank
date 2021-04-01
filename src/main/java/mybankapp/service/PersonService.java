package mybankapp.service;

import mybankapp.dto.CurrencyAccountDTO;
import mybankapp.dto.PersonDTO;
import mybankapp.dto.TransactionDTO;
import mybankapp.exception.MyBusinessException;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Person getPersonByName(String name) throws MyBusinessException;

    UUID createPerson(Person person) throws MyBusinessException;
    ResponseEntity<PersonDTO> getPerson(UUID personUUID) throws MyBusinessException;
    List<PersonDTO> getAllPersons();
    ResponseEntity<CurrencyAccountDTO> addAccount(CurrencyAccount account, UUID personUUID) throws MyBusinessException;
    Optional<CurrencyAccount> getAccount(long accounId) throws MyBusinessException;
    List<CurrencyAccountDTO> getAllAccounts(UUID personUUID);
    List<TransactionDTO> getAccountTransactions(long accountId) throws MyBusinessException;
    void deletePerson(UUID personUUID) throws MyBusinessException;
    void deleteAccount(long accountId) throws MyBusinessException;
    ResponseEntity<TransactionDTO> addTransaction(Transaction transaction, long accountId) throws MyBusinessException;
    ResponseEntity<String> updatePerson(Person person, UUID uuid) throws MyBusinessException;
}
