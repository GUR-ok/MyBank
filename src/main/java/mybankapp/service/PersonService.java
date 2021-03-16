package mybankapp.service;

import mybankapp.dao.PersonDAO;
import mybankapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    public void createPerson(Person person){
        personDAO.create(person);
    }

    public Person getPerson(int id){
        return personDAO.find(id);
    }

    /*
    public Person getPerson(String uuid){
        return personDAO.find(uuid);
    }*/

}
