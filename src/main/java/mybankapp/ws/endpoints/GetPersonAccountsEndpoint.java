package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.getpersonaccounts.wsdl.GetPersonAccountsRequest;
import mybankapp.ws.org.mybankapp.getpersonaccounts.wsdl.GetPersonAccountsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class GetPersonAccountsEndpoint {
    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonAccountsRequest")
    @ResponsePayload
    public GetPersonAccountsResponse getPersonAccounts(@RequestPayload GetPersonAccountsRequest request) {
        GetPersonAccountsResponse response = new GetPersonAccountsResponse();
        response.setCurrencyAccountDTO(personService.getAllAccounts(UUID.fromString(request.getUuid())));
        return response;
    }
}
