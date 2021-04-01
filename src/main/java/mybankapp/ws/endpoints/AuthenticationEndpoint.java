package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.dto.AuthenticationRequestDTO;
import mybankapp.exception.MyBusinessException;
import mybankapp.security.AuthService;

import mybankapp.ws.org.mybankapp.auth.wsdl.LoginRequest;
import mybankapp.ws.org.mybankapp.auth.wsdl.LoginResponse;
import mybankapp.ws.org.mybankapp.auth.wsdl.RefreshRequest;
import mybankapp.ws.org.mybankapp.auth.wsdl.RefreshResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Map;

@Endpoint
@RequiredArgsConstructor
public class AuthenticationEndpoint {

    private final AuthService authService;

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/auth/wsdl";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setAccessData((Map<Object, Object>) authService.login(new AuthenticationRequestDTO(request.getUsername(), request.getPassword())).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "refreshRequest")
    @ResponsePayload
    public RefreshResponse refreshtoken(@RequestPayload RefreshRequest request) throws MyBusinessException {
        RefreshResponse response = new RefreshResponse();
        response.setNewAccessToken(authService.simpleRefresh(request.getRefreshtoken()));
        return response;
    }

}
