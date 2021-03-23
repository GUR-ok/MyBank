package mybankapp.service;

import mybankapp.dto.NewsArticleDTO;
import mybankapp.model.NewsArticle;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsService {

    NewsArticleDTO createNewsArticle(NewsArticle article);
    ResponseEntity<NewsArticleDTO> findNewsArticle(long articleId);
    void deleteNewsArticle(long articleId);
    ResponseEntity watchArticleByPerson(UUID uuid, long articleId);
    ResponseEntity unWatchArticleByPerson(UUID uuid, long articleId);
    ResponseEntity<List<NewsArticleDTO>> getNewsfeed(UUID uuid, int numberOfNews);
    ResponseEntity<List<NewsArticleDTO>> getArchive(UUID uuid, int numberOfNews);
}
