package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.PersonService;
import mybankapp.ws.org.mybankapp.getallpersons.wsdl.GetAllPersonResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class GetAllPersonsEndpoint {
    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPersonsRequest")
    @ResponsePayload
    public GetAllPersonResponse getAllPersons() {
        GetAllPersonResponse response = new GetAllPersonResponse();
        response.setPersonDTO(personService.getAllPersons());
        return response;
    }
}
