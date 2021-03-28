package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.NewsService;
import mybankapp.ws.org.mybankapp.shownews.wsdl.ShowNewsRequest;
import mybankapp.ws.org.mybankapp.shownews.wsdl.ShowNewsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class ShowNewsEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final NewsService service;

    @ResponsePayload
    public ShowNewsResponse showNews(@RequestPayload ShowNewsRequest request) {
        ShowNewsResponse response = new ShowNewsResponse();
        response.setNewsArticleDTO(service.getNewsfeed(UUID.fromString(request.getUuid()),request.getNumberOfNews()).getBody());
        return response;
    }
}
