package mybankapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybankapp.dto.NewsArticleDTO;
import mybankapp.dto.PersonDTO;
import mybankapp.model.NewsArticle;
import mybankapp.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
    public ResponseEntity<NewsArticleDTO> findNewsArticle(@PathVariable long id) {
        log.info("getNewsArticle through controller");
        if (service.findNewsArticle(id).isPresent())
            return ResponseEntity.ok(NewsArticleDTO.from(service.findNewsArticle(id).get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("{uuid}/{id}")
    public ResponseEntity watchNewsArticle(@PathVariable UUID uuid, @PathVariable long id) {
        log.info("watchNewsArticle through controller");
        if (service.findNewsArticle(id).isPresent()) {
            service.watchArticleByPerson(uuid, id);
            return ResponseEntity.ok().body(null);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable long id) {
        log.info("deleteNewsArticle through controller");
        if (service.findNewsArticle(id).isPresent()) {
            service.deleteNewsArticle(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("{uuid}/{id}/unwatch")
    public ResponseEntity unwatchNews(@PathVariable UUID uuid, @PathVariable long id) {
        log.info("unWatchNewsArticle through controller");
        if (service.findNewsArticle(id).isPresent()) {
            service.unWatchArticleByPerson(uuid, id);
            return ResponseEntity.ok().body(null);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("{uuid}/newsfeed")
    public ResponseEntity<List<NewsArticleDTO>> showNews(@PathVariable UUID uuid, @RequestParam int numberOfNews) {
        log.info("showNews through controller");
        List<NewsArticleDTO> result = service.getNewsfeed(uuid, numberOfNews)
                .stream()
                .map(NewsArticleDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("{uuid}/archive")
    public ResponseEntity<List<NewsArticleDTO>> showArchive(@PathVariable UUID uuid, @RequestParam int numberOfNews) {
        log.info("showArchive through controller");
        List<NewsArticleDTO> result = service.getArchive(uuid, numberOfNews)
                .stream()
                .map(NewsArticleDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
