package mybankapp.dao;

import mybankapp.model.Person;

public interface PersonDAO {

    Person find(int uuid);
    // Person find(String uuid);
    void create(Person person);
}
