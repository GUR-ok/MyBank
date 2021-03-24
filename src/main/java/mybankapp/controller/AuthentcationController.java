package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import mybankapp.dto.AuthenticationRequestDTO;
import mybankapp.model.Person;
import mybankapp.security.jwt.JwtTokenProvider;
import mybankapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank")
public class AuthentcationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PersonService personService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO dto){
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            Person person = personService.getPersonByName(username);

            if (person == null) {
                throw new UsernameNotFoundException("user not found");
            }

            String token = jwtTokenProvider.createToken(username, person.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
