package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.AccountDAO;
import mybankapp.dao.PersonDAO;
import mybankapp.dao.TransactionDAO;
import mybankapp.dto.CurrencyAccountDTO;
import mybankapp.dto.PersonDTO;
import mybankapp.dto.TransactionDTO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    @Override
    public UUID createPerson(Person person){
        return personDAO.create(person);
    }

    @Override
    public ResponseEntity<PersonDTO> getPerson(UUID personUUID){
        if (personDAO.find(personUUID).isPresent()) {
            PersonDTO dto = PersonDTO.from(personDAO.find(personUUID).get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> result = personDAO.findAll()
                .stream()
                .map(PersonDTO::from)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ResponseEntity<CurrencyAccountDTO> addAccount(CurrencyAccount account, UUID personUUID) {
        Optional<Person> optionalPerson = personDAO.find(personUUID);
        if (optionalPerson.isPresent()) {
            optionalPerson.get().getAccounts().add(account);
            account.setOwner(optionalPerson.get());
            accountDAO.createAccount(account);
            return ResponseEntity.ok(CurrencyAccountDTO.from(account));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<CurrencyAccount> getAccount(long accounId) {
        return accountDAO.findAccount(accounId);
    }

    @Override
    public List<CurrencyAccountDTO> getAllAccounts(UUID personUUID) {
        List<CurrencyAccountDTO> result = new ArrayList<>();
        if (personDAO.find(personUUID).isPresent()) {
            result = personDAO.getPersonAccounts(personUUID)
                    .stream()
                    .map(CurrencyAccountDTO::from)
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public List<TransactionDTO> getAccountTransactions(long accountId) {
        List<TransactionDTO> result = new ArrayList<>();
        if (accountDAO.findAccount(accountId).isPresent()) {
            result = accountDAO.getAccountTransactions(accountId)
                    .stream()
                    .map(TransactionDTO::from)
                    .collect(Collectors.toList());
        }
        return result;
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
    public ResponseEntity<TransactionDTO> addTransaction(Transaction transaction, long accountId) {
        Optional<CurrencyAccount> account = accountDAO.findAccount(accountId);
        if (account.isPresent()){
            account.get().getTransactions().add(transaction);
            transaction.setAccount(account.get());
            Double amount = account.get().getBalance();
            amount += transaction.getAmount();
            account.get().setBalance(amount);
            transactionDAO.createTransaction(transaction);
            return ResponseEntity.ok(TransactionDTO.from(transaction));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> updatePerson(Person person, UUID uuid) {
        Optional<Person> optionalPerson = personDAO.find(uuid);
        if (optionalPerson.isPresent()) {
            Person newPerson = optionalPerson.get();
            newPerson.setName(person.getName());
            personDAO.create(newPerson);
            return ResponseEntity.ok("Person " + uuid.toString() + " updated");
        }
        return ResponseEntity.notFound().build();
    }
}
