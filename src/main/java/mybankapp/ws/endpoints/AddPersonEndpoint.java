package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.addperson.wsdl.AddPersonRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class AddPersonEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public String addPerson(@RequestPayload AddPersonRequest request) {
        return personService.createPerson(request.getPersonDTO().toPerson()).toString();
    }
}
