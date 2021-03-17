package mybankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private CurrencyAccount account;
}
