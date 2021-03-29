package mybankapp.model;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    private List<CurrencyAccount> accounts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "watched_news",
            joinColumns = @JoinColumn(name = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    private Set<NewsArticle> watchedNews = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
