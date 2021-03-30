package mybankapp.ws.endpoints;

import lombok.RequiredArgsConstructor;
import mybankapp.model.NewsArticle;
import mybankapp.service.NewsService;

import mybankapp.ws.org.mybankapp.news.wsdl.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
@RequiredArgsConstructor
public class NewsEndpoint {

    private static final String NAMESPACE_URI = "http://www.mybankapp.org/news/wsdl";

    private final NewsService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addNewsArticleRequest")
    @ResponsePayload
    public AddNewsArticleResponse addNewsArticle(@RequestPayload AddNewsArticleRequest request) {
        AddNewsArticleResponse response = new AddNewsArticleResponse();
        response.setNewsArticleDTO(service.createNewsArticle(new NewsArticle(request.getTitle(), request.getShorttext(), request.getFulltext(), request.getLinktoimg(), request.getButtonname(), request.getActual(),request.getUrl())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findNewsArticleRequest")
    @ResponsePayload
    public FindNewsArticleResponse findNewsArticle(@RequestPayload FindNewsArticleRequest request) {
        FindNewsArticleResponse response = new FindNewsArticleResponse();
        response.setNewsArticleDTO(service.findNewsArticle(request.getNewsId()).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "watchNewsArticleRequest")
    @ResponsePayload
    public WatchNewsArticleResponse watchNewsArticle(@RequestPayload WatchNewsArticleRequest request) {
        WatchNewsArticleResponse response = new WatchNewsArticleResponse();
        response.setNewsId(request.getNewsId());
        service.watchArticleByPerson(UUID.fromString(request.getUuid()),request.getNewsId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteNewsArticleRequest")
    @ResponsePayload
    public DeleteNewsArticleResponse deleteNewsArticle(@RequestPayload DeleteNewsArticleRequest request) {
        DeleteNewsArticleResponse response = new DeleteNewsArticleResponse();
        response.setNewsId(request.getNewsId());
        service.deleteNewsArticle(request.getNewsId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "unwatchNewsArticleRequest")
    @ResponsePayload
    public UnwatchNewsArticleResponse unwatchNewsArticle(@RequestPayload UnwatchNewsArticleRequest request) {
        UnwatchNewsArticleResponse response = new UnwatchNewsArticleResponse();
        response.setNewsId(request.getNewsId());
        service.unWatchArticleByPerson(UUID.fromString(request.getUuid()),request.getNewsId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "showNewsRequest")
    @ResponsePayload
    public ShowNewsResponse showNews(@RequestPayload ShowNewsRequest request) {
        ShowNewsResponse response = new ShowNewsResponse();
        response.setNewsArticleDTO(service.getNewsfeed(UUID.fromString(request.getUuid()),request.getNumberOfNews()).getBody());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "showArchiveRequest")
    @ResponsePayload
    public ShowArchiveResponse showArchive(@RequestPayload ShowArchiveRequest request) {
        ShowArchiveResponse response = new ShowArchiveResponse();
        response.setNewsArticleDTO(service.getArchive(UUID.fromString(request.getUuid()),request.getNumberOfNews()).getBody());
        return response;
    }
}
