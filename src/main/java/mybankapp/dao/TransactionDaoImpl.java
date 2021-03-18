package mybankapp.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.model.Transaction;
import mybankapp.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionDaoImpl implements TransactionDao{

    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.saveAndFlush(transaction);
    }

    @Override
    public Optional<Transaction> findTransaction(long transactionId) {
        return transactionRepository.findById(transactionId);
    }
}
