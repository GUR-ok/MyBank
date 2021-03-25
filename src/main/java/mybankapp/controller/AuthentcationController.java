package mybankapp.controller;

import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.dto.AuthenticationRequestDTO;
import mybankapp.model.Person;
import mybankapp.security.JwtUserDetailsService;
import mybankapp.security.jwt.JwtTokenProvider;
import mybankapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/bank")
public class AuthentcationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PersonService personService;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO dto){
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
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "/refreshtoken/")
    public ResponseEntity refreshToken(HttpServletRequest request){

            System.out.println("IN REFRESH CONTROLLER!!");
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
}
