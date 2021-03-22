package mybankapp.scheduled;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import mybankapp.dao.NewsArticleDAO;
import mybankapp.model.NewsArticle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class NewsScheduler {

    private final NewsArticleDAO newsArticleDAO;

    //Запуск каждые 5 минут
    @Scheduled(fixedRate = 1000*60*5)
    public void expireNews() {

        List<NewsArticle> news = newsArticleDAO.getNews();
        for (NewsArticle article : news) {
            //Если возраст новости больше 30 минут то isActual = false
            if ((System.currentTimeMillis()/1000/60-article.getDate().getTime()/1000/60)>30) {
                article.setIsActual(false);
                newsArticleDAO.createNewsArticle(article);
            }
        }

    }
}
