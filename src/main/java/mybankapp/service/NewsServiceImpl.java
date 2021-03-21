package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.NewsArticleDAO;
import mybankapp.model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsArticleDAO newsArticleDAO;

    @Override
    public void createNewsArticle(NewsArticle article) {
        newsArticleDAO.createNewsArticle(article);
    }

    @Override
    public Optional<NewsArticle> findNewsArticle(Long articleId) {
        return newsArticleDAO.findNewsArticle(articleId);
    }

    @Override
    public List<NewsArticle> getNews() {
        return newsArticleDAO.getNews();
    }

    @Override
    public void deleteNewsArticle(Long articleId) {
        newsArticleDAO.deleteNewsArticle(articleId);
    }

    @Override
    public void watchArticleByPerson(UUID uuid, Long articleId) {
        //WIP
    }
}
