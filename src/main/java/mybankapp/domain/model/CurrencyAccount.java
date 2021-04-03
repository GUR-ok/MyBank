package mybankapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.domain.enums.Currency;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class CurrencyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private long id;

    @Column(name = "account_balance", nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_currency", nullable = false)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", nullable = false)
    private Person owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", orphanRemoval = true)
    private List<Transaction> transactions;

    public CurrencyAccount(Currency currency, double balance) {
        this.currency = currency;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }
}
