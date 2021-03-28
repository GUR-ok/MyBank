package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.NewsService;
import mybankapp.ws.org.mybankapp.findnewsarticle.wsdl.FindNewsArticleRequest;
import mybankapp.ws.org.mybankapp.findnewsarticle.wsdl.FindNewsArticleResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class FindNewsArticleEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final NewsService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findNewsArticleRequest")
    @ResponsePayload
    public FindNewsArticleResponse findNewsArticle(@RequestPayload FindNewsArticleRequest request) {
        FindNewsArticleResponse response = new FindNewsArticleResponse();
        response.setNewsArticleDTO(service.findNewsArticle(request.getNewsId()).getBody());
        return response;
    }
}
