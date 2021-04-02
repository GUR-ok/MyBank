package mybankapp.service.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mybankapp.domain.model.Person;
import mybankapp.service.security.jwt.JwtUser;
import mybankapp.service.security.jwt.JwtUserFactory;
import mybankapp.service.PersonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final PersonService personService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Person person = personService.getPersonByName(userName);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(person);
        log.info("User {} loaded", userName);
        return jwtUser;
    }
}
