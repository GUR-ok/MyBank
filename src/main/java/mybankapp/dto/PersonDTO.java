package mybankapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mybankapp.model.Person;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "personDto")
@XmlType(propOrder = { "uuid", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO {

    @XmlElement
    private UUID uuid;
    @XmlElement
    private String name;
    @XmlTransient
    @JsonIgnore
    private String password;

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
        person.setPassword(this.password);
        return person;
    }
}


