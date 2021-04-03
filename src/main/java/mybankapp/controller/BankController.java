package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import mybankapp.domain.dto.CurrencyAccountDTO;
import mybankapp.domain.dto.PersonDTO;
import mybankapp.domain.dto.TransactionDTO;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.domain.model.CurrencyAccount;
import mybankapp.domain.model.Person;
import mybankapp.domain.model.Transaction;
import mybankapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/bank")
@Slf4j
public class BankController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) throws MyBusinessException {
        log.info("addPerson through controller");
        return ResponseEntity.ok(service.createPerson(person).toString());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable UUID uuid) throws MyBusinessException {
        log.info("getPerson through controller");
        return service.getPerson(uuid);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        log.info("getAllPersons through controller");
        return ResponseEntity.ok(service.getAllPersons());
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<CurrencyAccountDTO> addAccount(@RequestBody CurrencyAccount account, @PathVariable UUID uuid) throws MyBusinessException {
        log.info("addAccount through controller");
        return service.addAccount(account, uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletePerson(@PathVariable UUID uuid) throws MyBusinessException {
        log.info("deletePerson through controller");
        service.deletePerson(uuid);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable long accountId) throws MyBusinessException {
        log.info("deleteAccount through controller");
        service.deleteAccount(accountId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{uuid}/accounts")
    public ResponseEntity<List<CurrencyAccountDTO>> getPersonAccounts(@PathVariable UUID uuid) {
        log.info("getPersonAccounts through controller");
        return ResponseEntity.ok(service.getAllAccounts(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<String> updatePerson(@RequestBody Person person, @PathVariable UUID uuid) throws MyBusinessException {
        log.info("updatePerson through controller");
        return service.updatePerson(person, uuid);
    }

    @PostMapping("/accounts/{accountId}")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody Transaction transaction, @PathVariable Long accountId) throws MyBusinessException {
        log.info("addTransaction through controller");
        return service.addTransaction(transaction, accountId);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAccountTransactions(@PathVariable Long accountId) throws MyBusinessException {
        log.info("getAccountTransactions through controller");
        return ResponseEntity.ok(service.getAccountTransactions(accountId));
    }
}
