package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.domain.model.CurrencyAccount;
import mybankapp.domain.model.Person;
import mybankapp.domain.model.Transaction;
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
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) throws MyBusinessException {
        GetPersonResponse response = new GetPersonResponse();
        response.setPersonDTO(personService.getPerson(UUID.fromString(request.getUuid())).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) throws MyBusinessException {
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
    public AddAccountResponse addAccount(@RequestPayload AddAccountRequest request) throws MyBusinessException {
        AddAccountResponse response = new AddAccountResponse();
        response.setCurrencyAccountDTO(personService.addAccount(new CurrencyAccount(request.getCurrency(),request.getBalance()), UUID.fromString(request.getUuid())).getBody());
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
    public GetAccountTransactionsResponse getAccountTransactions(@RequestPayload GetAccountTransactionsRequest request) throws MyBusinessException {
        GetAccountTransactionsResponse response = new  GetAccountTransactionsResponse();
        response.setTransactionDTO(personService.getAccountTransactions(request.getAccountId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAccountRequest")
    @ResponsePayload
    public DeleteAccountResponse deleteAccount(@RequestPayload DeleteAccountRequest request) throws MyBusinessException {
        DeleteAccountResponse response = new DeleteAccountResponse();
        response.setAccountId(request.getAccountId());
        personService.deleteAccount(request.getAccountId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) throws MyBusinessException {
        DeletePersonResponse response = new DeletePersonResponse();
        response.setUuid(request.getUuid());
        personService.deletePerson(UUID.fromString(request.getUuid()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) throws MyBusinessException {
        UpdatePersonResponse response = new UpdatePersonResponse();
        response.setUuid(personService.updatePerson(new Person(request.getName(),request.getPassword()), UUID.fromString(request.getUuid())).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTransactionRequest")
    @ResponsePayload
    public AddTransactionResponse addTransaction(@RequestPayload AddTransactionRequest request) throws MyBusinessException {
        AddTransactionResponse response = new AddTransactionResponse();
        response.setTransactionDTO(personService.addTransaction(new Transaction(request.getAmount()), request.getAccountId()).getBody());
        return response;
    }
}
