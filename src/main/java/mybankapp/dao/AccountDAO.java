package mybankapp.dao;

import mybankapp.model.CurrencyAccount;
import mybankapp.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountDAO {

    void createAccount(CurrencyAccount account);
    Optional<CurrencyAccount> findAccount(long accountId);
    List<Transaction> getAccountTransactions(long accountId);
    void delete(long accountId);
}
