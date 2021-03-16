package mybankapp.dao;

import mybankapp.model.Person;
import mybankapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private PersonRepository repository;

    @Override
    public Person find(int id) {
      return (Person) repository.findById(id).get();
    }

      /*  @Override
    public Person find(String uuid) {
        return (Person) repository.findById(uuid).get();
    }*/

    @Override
    public void create(Person person) {
        repository.saveAndFlush(person);
    }
}
