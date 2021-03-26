package mybankapp.security.jwt;

import lombok.NoArgsConstructor;
import mybankapp.model.Person;
import mybankapp.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(Person person) {
        return new JwtUser(
                person.getUuid(),
                person.getName(),
                person.getPassword(),
                mapToGrandAuthorities(new ArrayList<>(person.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrandAuthorities(List<Role> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
