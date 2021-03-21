package mybankapp.service;

import mybankapp.model.NewsArticle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsService {

    void createNewsArticle(NewsArticle article);
    Optional<NewsArticle> findNewsArticle(Long articleId);
    List<NewsArticle> getNews();
    void deleteNewsArticle(Long articleId);
    void watchArticleByPerson(UUID uuid, Long articleId);
}
