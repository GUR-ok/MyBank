package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.addaccount.wsdl.AddAccountRequest;
import mybankapp.ws.org.mybankapp.addaccount.wsdl.AddAccountResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class AddAccountEndpoint {
    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAccountRequest")
    @ResponsePayload
    public AddAccountResponse addAccount(@RequestPayload AddAccountRequest request) {
        AddAccountResponse response = new AddAccountResponse();
        response.setCurrencyaccountDTO(personService.addAccount(request.getCurrencyaccountDTO().toCurrencyAccount(),UUID.fromString(request.getUuid())).getBody());
        return response;
    }
}
