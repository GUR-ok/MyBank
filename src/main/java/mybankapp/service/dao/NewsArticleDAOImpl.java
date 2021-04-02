package mybankapp.service.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.domain.model.NewsArticle;
import mybankapp.repository.NewsArticleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewsArticleDAOImpl implements NewsArticleDAO{

    private final NewsArticleRepository newsArticleRepository;


    @Override
    public void createNewsArticle(NewsArticle article) {
        newsArticleRepository.saveAndFlush(article);
    }

    @Override
    public Optional<NewsArticle> findNewsArticle(long articleId) {
        return newsArticleRepository.findById(articleId);
    }

    @Override
    public List<NewsArticle> getNews() {
        return newsArticleRepository.findAll();
    }

    @Override
    public void deleteNewsArticle(long articleId) {
        newsArticleRepository.deleteById(articleId);
    }
}
