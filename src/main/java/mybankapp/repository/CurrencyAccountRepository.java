package mybankapp.repository;

import mybankapp.domain.model.CurrencyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyAccountRepository extends JpaRepository<CurrencyAccount, Long> {
}
