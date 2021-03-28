package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.NewsService;
import mybankapp.ws.org.mybankapp.deletenewsarticle.wsdl.DeleteNewsArticleRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class DeleteNewsArticleEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final NewsService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteNewsArticleRequest")
    @ResponsePayload
    public Long deleteNewsArticle(@RequestPayload DeleteNewsArticleRequest request) {
        service.deleteNewsArticle(request.getNewsId());
        return request.getNewsId();
    }
}
