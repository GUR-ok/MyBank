package mybankapp.service.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.domain.model.Transaction;
import mybankapp.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionDAOImpl implements TransactionDAO {

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
