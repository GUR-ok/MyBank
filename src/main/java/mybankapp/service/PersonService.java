package mybankapp.service;

import mybankapp.domain.dto.CurrencyAccountDTO;
import mybankapp.domain.dto.PersonDTO;
import mybankapp.domain.dto.TransactionDTO;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.domain.model.CurrencyAccount;
import mybankapp.domain.model.Person;
import mybankapp.domain.model.Transaction;
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
