package mybankapp.dao;

import lombok.RequiredArgsConstructor;
import mybankapp.model.Transaction;
import mybankapp.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionDAOImpl implements TransactionDAO {

    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Transaction transaction) {
        transaction.setDate(new Date());
        transactionRepository.saveAndFlush(transaction);
    }

    @Override
    public Optional<Transaction> findTransaction(long transactionId) {
        return transactionRepository.findById(transactionId);
    }
}
