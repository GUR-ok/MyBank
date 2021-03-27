package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.addtransaction.wsdl.AddTransactionRequest;
import mybankapp.ws.org.mybankapp.addtransaction.wsdl.AddTransactionResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class AddTransactionEndpoint {
    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTransactionRequest")
    @ResponsePayload
    public AddTransactionResponse addTransaction(@RequestPayload AddTransactionRequest request) {
        AddTransactionResponse response = new AddTransactionResponse();
        response.setTransactionDTO(personService.addTransaction(request.getTransactionDTO().toTransaction(), request.getAccountId()).getBody());
        return response;
    }
}
