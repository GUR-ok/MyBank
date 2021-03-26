package mybankapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class AuthenticationRequestDTO {
    private String username;
    private String password;

    @Autowired
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