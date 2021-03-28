package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.NewsService;
import mybankapp.ws.org.mybankapp.unwatchnewsarticle.wsdl.UnwatchNewsArticleRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class UnwatchNewsArticleEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final NewsService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "unwatchNewsArticleRequest")
    @ResponsePayload
    public Long unwatchNewsArticle(@RequestPayload UnwatchNewsArticleRequest request) {
        service.unWatchArticleByPerson(UUID.fromString(request.getUuid()),request.getNewsId());
        return request.getNewsId();
    }
}
