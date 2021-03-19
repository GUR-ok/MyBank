package mybankapp.dao;

import mybankapp.model.Transaction;

import java.util.Optional;

public interface TransactionDAO {

    void createTransaction(Transaction transaction);
    Optional<Transaction> findTransaction(long transactionId);

}
