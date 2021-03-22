package mybankapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "short_text", nullable = false)
    private String shortDescription;

    @Column(name = "full_text", nullable = false)
    private String fullDescription;

    @Column(name = "link_to_img", nullable = false)
    private String linkToImg;

    @Column(name = "button_name", nullable = false)
    private String buttonName;

    @Column(name = "url", nullable = false)
    private URL url;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date = new Date();

    @Column(name = "is_actual", nullable = false)
    private Boolean isActual;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "watched_news",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "uuid"))
    private Set<Person> whoWatched = new HashSet<>();
}
