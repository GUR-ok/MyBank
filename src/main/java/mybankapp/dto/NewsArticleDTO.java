package mybankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.NewsArticle;

import java.net.URL;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsArticleDTO {

    private Long id;
    private String title;
    private String shortText;
    private String fullText;
    private String linkToImg;
    private String buttonName;
    private URL url;
    private Date date;

    public static NewsArticleDTO from(NewsArticle article) {
        NewsArticleDTO dto = new NewsArticleDTO();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setShortText(article.getShortDescription());
        dto.setFullText(article.getFullDescription());
        dto.setLinkToImg(article.getLinkToImg());
        dto.setButtonName(article.getButtonName());
        dto.setUrl(article.getUrl());
        dto.setDate(article.getDate());
        return dto;
    }
}
