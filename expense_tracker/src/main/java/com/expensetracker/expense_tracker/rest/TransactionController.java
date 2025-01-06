package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.Transaction;
import com.expensetracker.expense_tracker.entity.Category;
import com.expensetracker.expense_tracker.entity.TypeOfTransaction;
import com.expensetracker.expense_tracker.entity.User;
import com.expensetracker.expense_tracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
    private List<Transaction> getAllTransactions(@RequestParam(required = false) TypeOfTransaction typeOfTransaction) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            int id = transactionService.findIdByUsername(username);
            if (typeOfTransaction == null) {
                return transactionService.getTransactionsFromUser(id);
            } else {
                return transactionService.getTransactionsByType(typeOfTransaction, id);
            }
        }
        else return List.of();
    }

    @GetMapping("/transaction/{categoryId}/{typeOfTransaction}")
    private List<Transaction> getAllTransactionsForCategory(@PathVariable int categoryId, @PathVariable TypeOfTransaction typeOfTransaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            int id = transactionService.findIdByUsername(username);
            return transactionService.getTypeOfTransactionsByCategory(categoryId, id, typeOfTransaction);
        }
        else return List.of();
    }

    @GetMapping("/transaction/{transactionId}")
    private Transaction getTransactionById(@PathVariable int transactionId) {
        Transaction transaction = transactionService.findTransactionById(transactionId);

        if (transaction == null) {
            throw new RuntimeException("Did not find the transaction id: " + transactionId);
        }
        return transaction;
    }

    @PostMapping("/transaction")
    private Transaction addTransaction(@RequestBody Transaction transaction) {
        if (transaction.getCategory() != null && transaction.getCategory().getId() > 0) {
            Category category = transactionService.findCategoryById(transaction.getCategory().getId());
            transaction.setCategory(category);
        } else {
            throw new RuntimeException("Category ID is missing or invalid");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            int id = transactionService.findIdByUsername(username);
            User user = transactionService.findUserById(id);
            transaction.setUser(user);
        }
        else {
            throw new RuntimeException("User ID is missing or invalid");
        }

        return transactionService.saveTransaction(transaction);
    }

    @PutMapping("/transaction")
    private Transaction updateTransaction(@RequestBody Transaction transaction) {
        if (transaction.getCategory() != null && transaction.getCategory().getId() > 0) {
            Category category = transactionService.findCategoryById(transaction.getCategory().getId());
            transaction.setCategory(category);
        } else {
            throw new RuntimeException("Category ID is missing or invalid");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            int id = transactionService.findIdByUsername(username);
            User user = transactionService.findUserById(id);
            transaction.setUser(user);
        }
        else {
            throw new RuntimeException("User ID is missing or invalid");
        }

        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/transaction/{transactionId}")
    private void deleteTransaction(@PathVariable int transactionId) {
        Transaction transaction = transactionService.findTransactionById(transactionId);

        if (transaction == null) {
            throw new RuntimeException("Did not find the transaction id: " + transactionId);
        }

        transactionService.deleteTransaction(transactionId);
    }

    private String convertToCSV(List<Transaction> transactions) {
        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append("Category,Amount, PaymentType, Date, Note, TypeOfTransaction\n");

        for (Transaction transaction : transactions) {
            csvBuilder.append(transaction.getCategory())
                    .append(",")
                    .append(transaction.getAmount())
                    .append(",")
                    .append(transaction.getPaymentType())
                    .append(",")
                    .append(transaction.getDate())
                    .append(",")
                    .append(transaction.getNote())
                    .append(",")
                    .append(transaction.getTypeOfTransaction())
                    .append("\n");
        }

        return csvBuilder.toString();
    }
}
