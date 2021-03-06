package mybankapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.domain.model.NewsArticle;

import javax.xml.bind.annotation.*;
import java.net.URL;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "newsArticleDto")
@XmlType(propOrder = { "id", "title", "shortText", "fullText", "linkToImg", "buttonName", "url", "date"})
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsArticleDTO {
    @XmlAttribute
    private Long id;
    @XmlElement
    private String title;
    @XmlElement
    private String shortText;
    @XmlElement
    private String fullText;
    @XmlElement
    private String linkToImg;
    @XmlElement
    private String buttonName;
    @XmlElement
    private URL url;
    @XmlElement(name = "date")
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

    public NewsArticle toArticle() {
        NewsArticle article = new NewsArticle();
        article.setId(this.id);
        article.setTitle(this.title);
        article.setShortDescription(this.shortText);
        article.setFullDescription(this.fullText);
        article.setLinkToImg(this.linkToImg);
        article.setButtonName(this.buttonName);
        article.setUrl(this.url);
        article.setDate(this.date);
        return article;
    }
}
