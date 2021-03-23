package mybankapp.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Person;
import mybankapp.repository.PersonRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO{

    private final PersonRepository personRepository;

    @Override
    public Optional<Person> find(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public UUID create(Person person) {
        personRepository.saveAndFlush(person);
        return person.getUuid();
    }

    @Override
    public List<CurrencyAccount> getPersonAccounts(UUID id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        List<CurrencyAccount> list = new ArrayList<>();
        if (optionalPerson.isPresent()) {
            list = optionalPerson.get().getAccounts();
        }
        return list;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        personRepository.deleteById(id);
    }
}
