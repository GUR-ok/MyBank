package mybankapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.dao.AccountDAO;
import mybankapp.dao.PersonDAO;
import mybankapp.dao.RoleDAO;
import mybankapp.dao.TransactionDAO;
import mybankapp.dto.CurrencyAccountDTO;
import mybankapp.dto.PersonDTO;
import mybankapp.dto.TransactionDTO;
import mybankapp.exception.MyBusinessException;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Role;
import mybankapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;
    private final RoleDAO roleDAO;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptpasswordEncoder = new BCryptPasswordEncoder();
        return bCryptpasswordEncoder;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Person getPersonByName(String name) throws MyBusinessException {
        try {
            return personDAO.findByName(name).get();
        }
        catch (Exception e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }
    }

    @Override
    public UUID createPerson(Person person) throws MyBusinessException {
        try {
            Role roleUser = roleDAO.findByName("ROLE_USER").get();
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            person.setRoles(userRoles);
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            return personDAO.create(person);
        }  catch (IllegalArgumentException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"BAD REQUEST");
        }
    }

    @Override
    public ResponseEntity<PersonDTO> getPerson(UUID personUUID) throws MyBusinessException {
       /*    if (personDAO.find(personUUID).isPresent()) {
        *       PersonDTO dto = PersonDTO.from(personDAO.find(personUUID).get());
        *       return ResponseEntity.ok(dto);
        *   }
        *   return ResponseEntity.notFound().build();
        * */

        try {
            PersonDTO dto = PersonDTO.from(personDAO.find(personUUID).get());
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }

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
    public ResponseEntity<CurrencyAccountDTO> addAccount(CurrencyAccount account, UUID personUUID) throws MyBusinessException {
    /*    Optional<Person> optionalPerson = personDAO.find(personUUID);
    *    if (optionalPerson.isPresent()) {
    *        //optionalPerson.get().getAccounts().add(account);
    *        account.setOwner(optionalPerson.get());
    *        accountDAO.createAccount(account);
    *        return ResponseEntity.ok(CurrencyAccountDTO.from(account));
    *    }
    *    return ResponseEntity.notFound().build();
    */

        try {
            account.setOwner(personDAO.find(personUUID).get());
            accountDAO.createAccount(account);
            return ResponseEntity.ok(CurrencyAccountDTO.from(account));
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }  catch (IllegalArgumentException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"BAD REQUEST");
        }

    }

    @Override
    public Optional<CurrencyAccount> getAccount(long accounId) throws MyBusinessException {
        try {
            return accountDAO.findAccount(accounId);
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public List<TransactionDTO> getAccountTransactions(long accountId) throws MyBusinessException {
        List<TransactionDTO> result = new ArrayList<>();
        try {
            result = accountDAO.getAccountTransactions(accountId)
                    .stream()
                    .map(TransactionDTO::from)
                    .collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }
        return result;
    }

    @Override
    public void deletePerson(UUID personUUID) throws MyBusinessException {
        try {
        personDAO.delete(personUUID);
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }
    }

    @Override
    public void deleteAccount(long accountId) throws MyBusinessException {
        try {
            accountDAO.delete(accountId);
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<TransactionDTO> addTransaction(Transaction transaction, long accountId) throws MyBusinessException {
     /*   Optional<CurrencyAccount> account = accountDAO.findAccount(accountId);
     *   if (account.isPresent()){
     *       account.get().getTransactions().add(transaction);
     *       transaction.setAccount(account.get());
     *       Double amount = account.get().getBalance();
     *       amount += transaction.getAmount();
     *       account.get().setBalance(amount);
     *       transactionDAO.createTransaction(transaction);
     *       return ResponseEntity.ok(TransactionDTO.from(transaction));
     *  }
     *   return ResponseEntity.notFound().build();
     */

        try {
            CurrencyAccount account =  accountDAO.findAccount(accountId).get();
            account.getTransactions().add(transaction);
            transaction.setAccount(account);
            Double amount = account.getBalance();
            amount += transaction.getAmount();
            account.setBalance(amount);
            transactionDAO.createTransaction(transaction);
            return ResponseEntity.ok(TransactionDTO.from(transaction));
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }  catch (IllegalArgumentException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"BAD REQUEST");
        }

    }

    @Override
    public ResponseEntity<String> updatePerson(Person person, UUID uuid) throws MyBusinessException {
    /*    Optional<Person> optionalPerson = personDAO.find(uuid);
     *   if (optionalPerson.isPresent()) {
     *       Person newPerson = optionalPerson.get();
     *       newPerson.setName(person.getName());
     *       newPerson.setPassword(passwordEncoder.encode(person.getPassword()));
     *       personDAO.create(newPerson);
     *       return ResponseEntity.ok("Person " + uuid.toString() + " updated");
     *   }
     *   return ResponseEntity.notFound().build();
     */

        try {
            Person newPerson = personDAO.find(uuid).get();
            newPerson.setName(person.getName());
            newPerson.setPassword(passwordEncoder.encode(person.getPassword()));
            personDAO.create(newPerson);
            return ResponseEntity.ok("Person " + uuid.toString() + " updated");
        } catch (NoSuchElementException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"RESOURCE NOT FOUND");
        }  catch (IllegalArgumentException e) {
            throw new MyBusinessException(e.getMessage(),e.getCause(),"BAD REQUEST");
        }
    }

}
