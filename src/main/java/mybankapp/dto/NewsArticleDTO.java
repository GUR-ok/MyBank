package mybankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.NewsArticle;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsArticleDTO {

    private String title;
    private String shortText;
    private String fullText;

    public static NewsArticleDTO from(NewsArticle article) {
        NewsArticleDTO dto = new NewsArticleDTO();
        dto.setTitle(article.getTitle());
        dto.setShortText(article.getShortDescription());
        dto.setFullText(article.getFullDescription());
        return dto;
    }
}
