package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.NewsService;
import mybankapp.ws.org.mybankapp.showarchive.wsdl.ShowArchiveRequest;
import mybankapp.ws.org.mybankapp.showarchive.wsdl.ShowArchiveResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class ShowArchiveEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final NewsService service;

    @ResponsePayload
    public ShowArchiveResponse showArchive(@RequestPayload ShowArchiveRequest request) {
        ShowArchiveResponse response = new ShowArchiveResponse();
        response.setNewsArticleDTO(service.getNewsfeed(UUID.fromString(request.getUuid()),request.getNumberOfNews()).getBody());
        return response;
    }
}
