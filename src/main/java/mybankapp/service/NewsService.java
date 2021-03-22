package mybankapp.service;

import mybankapp.model.NewsArticle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsService {

    void createNewsArticle(NewsArticle article);
    Optional<NewsArticle> findNewsArticle(long articleId);
    void deleteNewsArticle(long articleId);
    void watchArticleByPerson(UUID uuid, long articleId);
    void unWatchArticleByPerson(UUID uuid, long articleId);
    List<NewsArticle> getNewsfeed(UUID uuid, int numberOfNews);
    List<NewsArticle> getArchive(UUID uuid, int numberOfNews);
}
