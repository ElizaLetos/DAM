package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.Transaction;
import com.expensetracker.expense_tracker.entity.TypeOfTransaction;

import java.util.List;

public interface TransactionDAOCustom {

    List<Transaction> getTransactionsByType(TypeOfTransaction type_of_transaction, int userId);

    int findIdByUsername(String name);

    List<Transaction> getTransactionsFromUser(int id);

}
