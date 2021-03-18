package mybankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.Person;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private UUID uuid;
    private String name;

    public static PersonDTO from(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setUuid(person.getUuid());
        dto.setName(person.getName());
        return dto;
    }

    public Person toPerson() {
        Person person = new Person();
        person.setUuid(this.uuid);
        person.setName(this.name);
        return person;
    }
}


