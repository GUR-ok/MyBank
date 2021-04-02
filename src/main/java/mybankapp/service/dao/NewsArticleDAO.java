package mybankapp.service.dao;

import mybankapp.domain.model.NewsArticle;

import java.util.List;
import java.util.Optional;

public interface NewsArticleDAO {

    void createNewsArticle(NewsArticle article);
    Optional<NewsArticle> findNewsArticle(long articleId);
    List<NewsArticle> getNews();
    void deleteNewsArticle(long articleId);

}
