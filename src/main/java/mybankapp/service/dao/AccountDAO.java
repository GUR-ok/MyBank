package mybankapp.service.dao;

import mybankapp.domain.model.CurrencyAccount;
import mybankapp.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountDAO {

    void createAccount(CurrencyAccount account);
    Optional<CurrencyAccount> findAccount(long accountId);
    List<Transaction> getAccountTransactions(long accountId);
    void delete(long accountId);
}
