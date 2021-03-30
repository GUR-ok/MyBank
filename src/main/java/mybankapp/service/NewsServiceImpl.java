package mybankapp.service;

import lombok.RequiredArgsConstructor;
import mybankapp.dao.NewsArticleDAO;
import mybankapp.dao.PersonDAO;
import mybankapp.dto.NewsArticleDTO;
import mybankapp.model.NewsArticle;
import mybankapp.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsArticleDAO newsArticleDAO;
    private final PersonDAO personDAO;

    @Override
    public NewsArticleDTO createNewsArticle(NewsArticle article) {
        newsArticleDAO.createNewsArticle(article);
        return NewsArticleDTO.from(article);
    }

    @Override
    public ResponseEntity<NewsArticleDTO> findNewsArticle(long articleId) {
        if (newsArticleDAO.findNewsArticle(articleId).isPresent())
            return ResponseEntity.ok(NewsArticleDTO.from(newsArticleDAO.findNewsArticle(articleId).get()));
        else
            return ResponseEntity.notFound().build();
    }


    @Override
    public void deleteNewsArticle(long articleId) {
        newsArticleDAO.deleteNewsArticle(articleId);
    }

    @Override
    @Transactional
    public ResponseEntity watchArticleByPerson(UUID uuid, long articleId) {
        if (!personDAO.find(uuid).isPresent() ||
                !newsArticleDAO.findNewsArticle(articleId).isPresent())
            return ResponseEntity.notFound().build();
        NewsArticle article = newsArticleDAO.findNewsArticle(articleId).get();
        Person person = personDAO.find(uuid).get();
        Set<Person> persons = article.getWhoWatched();
        persons.add(person);
        newsArticleDAO.createNewsArticle(article);
        return ResponseEntity.ok().body(null);
    }

    @Override
    @Transactional
    public ResponseEntity unWatchArticleByPerson(UUID uuid, long articleId) {
        if (!personDAO.find(uuid).isPresent() || !newsArticleDAO.findNewsArticle(articleId).isPresent())
            return ResponseEntity.notFound().build();
        NewsArticle article = newsArticleDAO.findNewsArticle(articleId).get();
        Person person = personDAO.find(uuid).get();
        Set<Person> persons = article.getWhoWatched();
        persons.remove(person);
        newsArticleDAO.createNewsArticle(article);
        return ResponseEntity.ok().body(null);
    }

    @Override
    @Transactional
    public ResponseEntity<List<NewsArticleDTO>> getNewsfeed(UUID uuid, int numberOfNews) {
        List<NewsArticle> list = new ArrayList<>();
        if (!personDAO.find(uuid).isPresent()) return ResponseEntity.notFound().build();
        Person person = personDAO.find(uuid).get();
        List<NewsArticle> newsDB = newsArticleDAO.getNews();
        for (NewsArticle article : newsDB ) {
            //Если новость актуальна по времени и юзер ее не смотрел
            if (article.getIsActual() == true && !article.getWhoWatched().contains(person))
                list.add(article);
            if (list.size() >= numberOfNews) break;
        }
        List<NewsArticleDTO> result = list
                .stream()
                .map(NewsArticleDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    @Transactional
    public ResponseEntity<List<NewsArticleDTO>> getArchive(UUID uuid, int numberOfNews) {
        List<NewsArticle> list = new ArrayList<>();
        if (!personDAO.find(uuid).isPresent()) return ResponseEntity.notFound().build();
        Person person = personDAO.find(uuid).get();
        List<NewsArticle> news = newsArticleDAO.getNews();
        for (NewsArticle article : news) {
            //Если новость просрочена по времени или юзер ее смотрел
            if (article.getIsActual() == false || article.getWhoWatched().contains(person))
                list.add(article);
            if (list.size() >= numberOfNews) break;
        }
        List<NewsArticleDTO> result = list
                .stream()
                .map(NewsArticleDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
