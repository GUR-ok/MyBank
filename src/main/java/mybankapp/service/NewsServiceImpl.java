package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.NewsArticleDAO;
import mybankapp.dao.PersonDAO;
import mybankapp.model.NewsArticle;
import mybankapp.model.Person;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsArticleDAO newsArticleDAO;
    private final PersonDAO personDAO;

    @Override
    public void createNewsArticle(NewsArticle article) {
        newsArticleDAO.createNewsArticle(article);
    }

    @Override
    public Optional<NewsArticle> findNewsArticle(long articleId) {
        return newsArticleDAO.findNewsArticle(articleId);
    }


    @Override
    public void deleteNewsArticle(long articleId) {
        newsArticleDAO.deleteNewsArticle(articleId);
    }

    @Override
    public void watchArticleByPerson(UUID uuid, long articleId) {
        if (!personDAO.find(uuid).isPresent() ||
                !newsArticleDAO.findNewsArticle(articleId).isPresent()) return;
        NewsArticle article = newsArticleDAO.findNewsArticle(articleId).get();
        Person person = personDAO.find(uuid).get();
        Set<Person> persons = article.getWhoWatched();
        persons.add(person);
        newsArticleDAO.createNewsArticle(article);
    }

    @Override
    public void unWatchArticleByPerson(UUID uuid, long articleId) {
        NewsArticle article = newsArticleDAO.findNewsArticle(articleId).get();
        if (!personDAO.find(uuid).isPresent()) return;
        Person person = personDAO.find(uuid).get();
        Set<Person> persons = article.getWhoWatched();
        persons.remove(person);
        newsArticleDAO.createNewsArticle(article);
    }

    @Override
    public List<NewsArticle> getNewsfeed(UUID uuid, int numberOfNews) {
        List<NewsArticle> list = new ArrayList<>();
        if (!personDAO.find(uuid).isPresent()) return list;
        Person person = personDAO.find(uuid).get();
        List<NewsArticle> newsDB = newsArticleDAO.getNews();
        for (NewsArticle article : newsDB ) {
            //Если новость актуальна по времени и юзер ее не смотрел
            if (article.getIsActual() == true && !article.getWhoWatched().contains(person))
                list.add(article);
            if (list.size() >= numberOfNews) break;
        }
        return list;
    }

    @Override
    public List<NewsArticle> getArchive(UUID uuid, int numberOfNews) {
        List<NewsArticle> list = new ArrayList<>();
        if (!personDAO.find(uuid).isPresent()) return list;
        Person person = personDAO.find(uuid).get();
        List<NewsArticle> news = newsArticleDAO.getNews();
        for (NewsArticle article : news) {
            //Если новость просрочена по времени или юзер ее смотрел
            if (article.getIsActual() == false || article.getWhoWatched().contains(person))
                list.add(article);
            if (list.size() >= numberOfNews) break;
        }
        return list;
    }
}
