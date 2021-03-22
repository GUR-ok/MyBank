package mybankapp.model;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Person {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private UUID uuid;

    @Column(name = "username", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "owner", orphanRemoval = true)
    private List<CurrencyAccount> accounts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "watched_news",
            joinColumns = @JoinColumn(name = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    private Set<NewsArticle> watchedNews = new HashSet<>();
}
