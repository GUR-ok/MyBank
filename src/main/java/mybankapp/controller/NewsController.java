package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.domain.dto.NewsArticleDTO;
import mybankapp.domain.exception.MyBusinessException;
import mybankapp.domain.model.NewsArticle;
import mybankapp.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
@Slf4j
public class NewsController {

    private final NewsService service;

    @PostMapping("/admin")
    public ResponseEntity<NewsArticleDTO> addNewsArticle(@RequestBody NewsArticle article) {
        log.info("addNewsArticle through controller");
        return ResponseEntity.ok(service.createNewsArticle(article));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticleDTO> findNewsArticle(@PathVariable long id) throws MyBusinessException {
        log.info("getNewsArticle through controller");
        return service.findNewsArticle(id);
    }

    @PostMapping("{uuid}/{id}")
    public ResponseEntity watchNewsArticle(@PathVariable UUID uuid, @PathVariable long id) throws MyBusinessException {
        log.info("watchNewsArticle through controller");
        return service.watchArticleByPerson(uuid, id);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable long id) throws MyBusinessException {
        log.info("deleteNewsArticle through controller");
            service.deleteNewsArticle(id);
            return ResponseEntity.ok().build();
    }

    @PostMapping("{uuid}/{id}/unwatch")
    public ResponseEntity unwatchNews(@PathVariable UUID uuid, @PathVariable long id) throws MyBusinessException {
        log.info("unWatchNewsArticle through controller");
        return service.unWatchArticleByPerson(uuid, id);
    }

    @GetMapping("{uuid}/newsfeed")
    public ResponseEntity<List<NewsArticleDTO>> showNews(@PathVariable UUID uuid, @RequestParam int numberOfNews) throws MyBusinessException {
        log.info("showNews through controller");
        return service.getNewsfeed(uuid, numberOfNews);
    }

    @GetMapping("{uuid}/archive")
    public ResponseEntity<List<NewsArticleDTO>> showArchive(@PathVariable UUID uuid, @RequestParam int numberOfNews) throws MyBusinessException {
        log.info("showArchive through controller");
        return service.getArchive(uuid, numberOfNews);
    }
}
