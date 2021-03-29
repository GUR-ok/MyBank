package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.model.Person;
import mybankapp.service.PersonService;

import mybankapp.ws.org.mybankapp.bank.wsdl.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class BankEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/bank/wsdl";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPersonDTO(personService.getPerson(UUID.fromString(request.getUuid())).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) {
        AddPersonResponse response = new AddPersonResponse();
        response.setUuid(personService.createPerson(new Person(request.getName(),request.getPassword())).toString());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPersonsRequest")
    @ResponsePayload
    public GetAllPersonsResponse getAllPersons() {
        GetAllPersonsResponse response = new GetAllPersonsResponse();
        response.setPersonDTO(personService.getAllPersons());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAccountRequest")
    @ResponsePayload
    public AddAccountResponse addAccount(@RequestPayload AddAccountRequest request) {
        AddAccountResponse response = new AddAccountResponse();
        response.setCurrencyAccountDTO(personService.addAccount(request.getCurrencyAccountDTO().toCurrencyAccount(),UUID.fromString(request.getUuid())).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonAccountsRequest")
    @ResponsePayload
    public GetPersonAccountsResponse getPersonAccounts(@RequestPayload GetPersonAccountsRequest request) {
        GetPersonAccountsResponse response = new GetPersonAccountsResponse();
        response.setCurrencyAccountDTO(personService.getAllAccounts(UUID.fromString(request.getUuid())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart =  "getAccountTransactionsRequest")
    @ResponsePayload
    public GetAccountTransactionsResponse getPersonAccounts(@RequestPayload GetAccountTransactionsRequest request) {
        GetAccountTransactionsResponse response = new  GetAccountTransactionsResponse();
        response.setTransactionDTO(personService.getAccountTransactions(request.getAccountId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAccountRequest")
    @ResponsePayload
    public Long deleteAccount(@RequestPayload DeleteAccountRequest request) {
        personService.deleteAccount(request.getAccountId());
        return request.getAccountId();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public String deletePerson(@RequestPayload DeletePersonRequest request) {
        personService.deletePerson(UUID.fromString(request.getUuid()));
        return request.getUuid();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public String updatePerson(@RequestPayload UpdatePersonRequest request) {
        return personService.updatePerson(request.getPersonDTO().toPerson(), UUID.fromString(request.getUuid())).getBody();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTransactionRequest")
    @ResponsePayload
    public AddTransactionResponse addTransaction(@RequestPayload AddTransactionRequest request) {
        AddTransactionResponse response = new AddTransactionResponse();
        response.setTransactionDTO(personService.addTransaction(request.getTransactionDTO().toTransaction(), request.getAccountId()).getBody());
        return response;
    }
}
