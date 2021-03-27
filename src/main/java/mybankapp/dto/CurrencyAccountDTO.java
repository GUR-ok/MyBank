package mybankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybankapp.model.Currency;
import mybankapp.model.CurrencyAccount;

import javax.xml.bind.annotation.*;
import java.util.UUID;
@NoArgsConstructor
@Data
@AllArgsConstructor
@XmlRootElement(name = "currencyAccountDTO")
@XmlType(propOrder = { "id", "balance", "ownerUuid", "currency"})
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyAccountDTO {

    @XmlAttribute
    private long id;

    @XmlElement
    private double balance;

    @XmlElement
    private UUID ownerUuid;

    @XmlElement
    private Currency currency;

    public static CurrencyAccountDTO from(CurrencyAccount account) {
        CurrencyAccountDTO dto = new CurrencyAccountDTO();
        dto.setId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setOwnerUuid(account.getOwner().getUuid());
        dto.setCurrency(account.getCurrency());
        return dto;
    }

    public CurrencyAccount toCurrencyAccount() {
        CurrencyAccount account = new CurrencyAccount();
        account.setId(this.id);
        account.setBalance(this.balance);
        account.setCurrency(this.currency);
        return account;
    }
}
