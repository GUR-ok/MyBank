package mybankapp.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.model.CurrencyAccount;
import mybankapp.model.Transaction;
import mybankapp.repository.CurrencyAccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountDaoImpl implements AccountDao{

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
