package mybankapp.dao;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.NewsArticle;

import java.util.List;
import java.util.Optional;

public interface NewsArticleDAO {

    void createNewsArticle(NewsArticle article);
    Optional<NewsArticle> findNewsArticle(long articleId);
    List<NewsArticle> getNews();
    void deleteNewsArticle(long articleId);

}
