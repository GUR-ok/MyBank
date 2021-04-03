package mybankapp.service;

import mybankapp.domain.dto.NewsArticleDTO;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.domain.model.NewsArticle;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NewsService {

    NewsArticleDTO createNewsArticle(NewsArticle article);
    ResponseEntity<NewsArticleDTO> findNewsArticle(long articleId) throws MyBusinessException;
    void deleteNewsArticle(long articleId) throws MyBusinessException;
    ResponseEntity watchArticleByPerson(UUID uuid, long articleId) throws MyBusinessException;
    ResponseEntity unWatchArticleByPerson(UUID uuid, long articleId) throws MyBusinessException;
    ResponseEntity<List<NewsArticleDTO>> getNewsfeed(UUID uuid, int numberOfNews) throws MyBusinessException;
    ResponseEntity<List<NewsArticleDTO>> getArchive(UUID uuid, int numberOfNews) throws MyBusinessException;
}
