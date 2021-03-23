package mybankapp.service;

import mybankapp.dto.CurrencyAccountDTO;
import mybankapp.dto.PersonDTO;
import mybankapp.dto.TransactionDTO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    UUID createPerson(Person person);
    ResponseEntity<PersonDTO> getPerson(UUID personUUID);
    List<PersonDTO> getAllPersons();
    ResponseEntity<CurrencyAccountDTO> addAccount(CurrencyAccount account, UUID personUUID);
    Optional<CurrencyAccount> getAccount(long accounId);
    List<CurrencyAccountDTO> getAllAccounts(UUID personUUID);
    List<TransactionDTO> getAccountTransactions(long accountId);
    void deletePerson(UUID personUUID);
    void deleteAccount(long accountId);
    ResponseEntity<TransactionDTO> addTransaction(Transaction transaction, long accountId);
    ResponseEntity<String> updatePerson(Person person, UUID uuid);
}
