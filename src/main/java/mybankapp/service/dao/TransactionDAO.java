package mybankapp.service.dao;

import mybankapp.domain.model.Transaction;

import java.util.Optional;

public interface TransactionDAO {

    void createTransaction(Transaction transaction);
    Optional<Transaction> findTransaction(long transactionId);

}
