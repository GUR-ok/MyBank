package mybankapp.repository;

import mybankapp.model.CurrencyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyAccountRepository extends JpaRepository<CurrencyAccount, Long> {
}
