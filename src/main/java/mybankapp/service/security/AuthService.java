package mybankapp.service.security;

import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.domain.dto.AuthenticationRequestDTO;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.domain.model.Person;
import mybankapp.service.security.jwt.JwtTokenProvider;
import mybankapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PersonService personService;

    public ResponseEntity login(AuthenticationRequestDTO dto) {
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            Person person = personService.getPersonByName(username);

            if (person == null) {
                throw new UsernameNotFoundException("user not found");
            }

            String accessToken = jwtTokenProvider.createToken(username, person.getRoles());
            String freshToken = jwtTokenProvider.createRefreshToken(username, person.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("Username", username);
            response.put("AccessToken", accessToken);
            response.put("RefreshToken", freshToken);

            return ResponseEntity.ok(response);
        } catch (
                AuthenticationException | MyBusinessException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public ResponseEntity refresh(HttpServletRequest request) throws MyBusinessException {

        //Get claims from request
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        String username = claims.get("sub", String.class);
        log.info("In refreshController. " + username);

        Person person = personService.getPersonByName(username);

        if (person == null) {
            throw new UsernameNotFoundException("user not found");
        }
        //Give new Access token!
        String token = jwtTokenProvider.createToken(username, person.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("New Access token", token);

        return ResponseEntity.ok(response);
    }

    public String simpleRefresh(String refreshtoken) throws MyBusinessException {
        String username = jwtTokenProvider.getUserName(refreshtoken);
        Person person = personService.getPersonByName(jwtTokenProvider.getUserName(refreshtoken));
        String newAccessToken = null;
        if (jwtTokenProvider.validateRefreshToken(refreshtoken)) {
            newAccessToken = jwtTokenProvider.createToken(username, person.getRoles());
        }
        return newAccessToken;
    }
}

