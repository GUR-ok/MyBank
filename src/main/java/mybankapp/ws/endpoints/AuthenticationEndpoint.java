package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.security.AuthService;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class AuthenticationEndpoint {

    private final AuthService authService;

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/auth/wsdl";

 /*   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();

        return ;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "refreshRequest")
    @ResponsePayload
    public RefreshResponse refreshtoken(@RequestPayload RefreshRequest request) {
        RefreshResponse response = new RefreshResponse();

        return ;
    }
*/
}
