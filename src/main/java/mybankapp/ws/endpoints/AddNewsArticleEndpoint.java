package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.service.NewsService;
import mybankapp.ws.org.mybankapp.addnewsarticle.wsdl.AddNewsArticleRequest;
import mybankapp.ws.org.mybankapp.addnewsarticle.wsdl.AddNewsArticleResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class AddNewsArticleEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/gen";

    private final NewsService service;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addNewsArticleRequest")
    @ResponsePayload
    public AddNewsArticleResponse addNewsArticle(@RequestPayload AddNewsArticleRequest request) {
        AddNewsArticleResponse response = new AddNewsArticleResponse();
        response.setNewsArticleDTO(service.createNewsArticle(request.getNewsArticleDTO().toArticle()));
        return response;
    }
}
