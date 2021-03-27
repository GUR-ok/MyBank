package mybankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.Person;
import mybankapp.model.Transaction;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "transactionDTO")
@XmlType(propOrder = { "id", "date", "amount"})
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionDTO {
    @XmlAttribute
    private Long id;
    @XmlElement
    private Date date;
    @XmlElement
    private Double amount;

    public static TransactionDTO from(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setAmount(transaction.getAmount());
        return dto;
    }

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(this.id);
        transaction.setDate(this.date);
        transaction.setAmount(this.amount);
        return transaction;
    }
}
