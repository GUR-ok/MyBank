package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import mybankapp.model.Person;
import mybankapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
@Slf4j
public class BankController {

    private final PersonService service;

    @PostMapping
    public void createUser(@RequestBody Person person) {
        log.info("add user");
        service.createPerson(person);
    }

}
