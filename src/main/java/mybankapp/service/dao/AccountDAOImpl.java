package mybankapp.service.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.domain.model.CurrencyAccount;
import mybankapp.domain.model.Transaction;
import mybankapp.repository.CurrencyAccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountDAOImpl implements AccountDAO {

    private final CurrencyAccountRepository currencyAccountRepository;

    @Override
    public void createAccount(CurrencyAccount account) {
        currencyAccountRepository.saveAndFlush(account);
    }

    @Override
    public Optional<CurrencyAccount> findAccount(long accountId) {
        return currencyAccountRepository.findById(accountId);
    }

    @Override
    public List<Transaction> getAccountTransactions(long accountId) {
        Optional<CurrencyAccount> optionalAccount = currencyAccountRepository.findById(accountId);
        List<Transaction> list = new ArrayList<>();
        if (optionalAccount.isPresent()) {
            list = optionalAccount.get().getTransactions();
        }
        return list;
    }

    @Override
    public void delete(long accountId) {
        currencyAccountRepository.deleteById(accountId);
    }
}
