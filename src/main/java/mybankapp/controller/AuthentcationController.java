package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.domain.dto.AuthenticationRequestDTO;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.service.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/bank")
public class AuthentcationController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO dto){
       return authService.login(dto);
    }

    @GetMapping(value = "/refreshtoken/")
    public ResponseEntity refreshToken(HttpServletRequest request) throws MyBusinessException {
        return authService.refresh(request);
    }
}
