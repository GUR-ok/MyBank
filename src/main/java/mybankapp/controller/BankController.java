package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import mybankapp.dto.CurrencyAccountDTO;
import mybankapp.dto.PersonDTO;
import mybankapp.dto.TransactionDTO;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.model.Transaction;
import mybankapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
@Slf4j
public class BankController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        log.info("addPerson through controller");
        service.createPerson(person);
        return ResponseEntity.ok(person.getUuid().toString());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable UUID uuid) {
        log.info("getPerson through controller");
        if (service.getPerson(uuid).isPresent())
            return ResponseEntity.ok(PersonDTO.from(service.getPerson(uuid).get()));
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        log.info("getAllPersons through controller");
        List<PersonDTO> result = service.getAllPersons()
                .stream()
                .map(PersonDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<CurrencyAccountDTO> addAccount(@RequestBody CurrencyAccount account, @PathVariable UUID uuid) {
        log.info("addAccount through controller");
        if (service.getPerson(uuid).isPresent()) {
            service.addAccount(account, uuid);
            return ResponseEntity.ok(CurrencyAccountDTO.from(account));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletePerson(@PathVariable UUID uuid) {
        log.info("deletePerson through controller");
        service.deletePerson(uuid);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable long accountId) {
        log.info("deleteAccount through controller");
        service.deleteAccount(accountId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{uuid}/accounts")
    public ResponseEntity<List<CurrencyAccountDTO>> getPersonAccounts(@PathVariable UUID uuid) {
        log.info("getPersonAccounts through controller");
        if (service.getPerson(uuid).isPresent()) {
            List<CurrencyAccountDTO> result = service.getAllAccounts(uuid)
                .stream()
                .map(CurrencyAccountDTO::from)
                .collect(Collectors.toList());
            return ResponseEntity.ok(result);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<String> updatePerson(@RequestBody Person person, @PathVariable UUID uuid) {
        log.info("updatePerson through controller");
        if (service.getPerson(uuid).isPresent()) {
            Person newPerson = service.getPerson(uuid).get();
            newPerson.setName(person.getName());
            service.createPerson(newPerson);
            return ResponseEntity.ok(newPerson.getUuid().toString());
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/accounts/{accountId}")
    public ResponseEntity<Long> addTransaction(@RequestBody Transaction transaction, @PathVariable Long accountId) {
        log.info("addTransaction through controller");
        if (service.getAccount(accountId).isPresent()) {
            service.addTransaction(transaction, accountId);
        return ResponseEntity.ok(TransactionDTO.from(transaction).getId());
    } else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAccountTransactions(@PathVariable Long accountId) {
        log.info("getAccountTransactions through controller");
        List<TransactionDTO> result = service.getAccountTransactions(accountId)
                .stream()
                .map(TransactionDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
