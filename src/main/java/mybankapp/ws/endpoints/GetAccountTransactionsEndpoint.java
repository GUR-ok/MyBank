package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.getaccounttransactions.wsdl.GetAccountTransactionsRequest;
import mybankapp.ws.org.mybankapp.getaccounttransactions.wsdl.GetAccountTransactionsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class GetAccountTransactionsEndpoint {
    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart =  "getAccountTransactionsRequest")
    @ResponsePayload
    public GetAccountTransactionsResponse getPersonAccounts(@RequestPayload GetAccountTransactionsRequest request) {
        GetAccountTransactionsResponse response = new  GetAccountTransactionsResponse();
        response.setTransactionDTO(personService.getAccountTransactions(request.getAccountId()));
        return response;
    }
}
