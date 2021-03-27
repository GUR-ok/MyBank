package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.deleteperson.wsdl.DeletePersonRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class DeletePersonEndpoint {
    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public String deletePerson(@RequestPayload DeletePersonRequest request) {
        personService.deletePerson(UUID.fromString(request.getUuid()));
        return request.getUuid();
    }
}
