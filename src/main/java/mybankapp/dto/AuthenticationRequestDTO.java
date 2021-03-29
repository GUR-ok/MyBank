package mybankapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement(name = "authenticationRequestDTO")
@XmlType(propOrder = { "username", "password"})
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthenticationRequestDTO {

    @XmlElement
    private String username;

    @XmlElement
    private String password;

    @Autowired
    @XmlTransient
    @JsonIgnore
    private BCryptPasswordEncoder passwordEncoder;

    public static AuthenticationRequestDTO from(Person person) {
        AuthenticationRequestDTO dto = new AuthenticationRequestDTO();
        dto.setUsername(person.getName());
        dto.setPassword(person.getPassword());
        return dto;
    }

    public Person toPerson() {
        Person person = new Person();
        person.setName(this.getUsername());
        person.setPassword(passwordEncoder.encode(this.getPassword()));
        return person;
    }
}
