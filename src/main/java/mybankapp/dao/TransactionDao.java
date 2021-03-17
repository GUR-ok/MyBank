package mybankapp.dao;

import mybankapp.model.Transaction;

import java.util.Optional;

public interface TransactionDao {

    public void createTransaction(Transaction transaction);
    Optional<Transaction> findTransaction(long transactionId);

}
