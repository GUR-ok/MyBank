package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.dto.NewsArticleDTO;
import mybankapp.model.NewsArticle;
import mybankapp.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
@Slf4j
public class NewsController {

    private final NewsService service;

    @PostMapping
    public ResponseEntity<Long> addNewsArticle(@RequestBody NewsArticle article) {
        log.info("addNewsArticle through controller");
        service.createNewsArticle(article);
        return ResponseEntity.ok(article.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticleDTO> findNewsArticle(@PathVariable Long id) {
        log.info("getNewsArticle through controller");
        if (service.findNewsArticle(id).isPresent())
            return ResponseEntity.ok(NewsArticleDTO.from(service.findNewsArticle(id).get()));
        else
            return ResponseEntity.notFound().build();
    }
}
