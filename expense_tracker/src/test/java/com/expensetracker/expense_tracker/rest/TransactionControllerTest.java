package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.*;
import com.expensetracker.expense_tracker.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.time.Instant;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TransactionControllerTest {
    private MockMvc mockMvc;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(authentication.getName()).thenReturn("mockUsername");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void getAllTransactions() throws Exception{
        User user = new User("Leslie", "leslie@gmail.com", "test123");

        Category category = new Category("gift", "first year anniversary");

        Date date = Date.from(Instant.now());

        Transaction transaction = new Transaction(user, category, 1000D, PaymentType.CASH, date, null, TypeOfTransaction.EXPENSE);

        when(transactionService.findIdByUsername("mockUsername")).thenReturn(0);
        when(transactionService.getTransactionsFromUser(0)).thenReturn(Collections.singletonList(transaction));

        mockMvc.perform(get("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category.name").value("gift"))
                .andExpect(jsonPath("$[0].amount").value(1000D))
                .andExpect(jsonPath("$[0].paymentType").value(PaymentType.CASH.toString()))
                .andExpect(jsonPath("$[0].date").value(date))
                .andExpect(jsonPath("$[0].typeOfTransaction").value(TypeOfTransaction.EXPENSE.toString()));
    }


    @Test
    void getUserById() throws Exception {
        User user = new User("Leslie", "leslie@gmail.com", "test123");

        Category category = new Category("gift", "first year anniversary");

        Date date = Date.from(Instant.now());

        Transaction transaction = new Transaction(user, category, 2000D, PaymentType.CREDIT_CARD, date, null, TypeOfTransaction.INCOME);

        when(transactionService.findTransactionById(0)).thenReturn(transaction);

        mockMvc.perform(get("/api/transaction/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category.name").value("gift"))
                .andExpect(jsonPath("$.amount").value(2000D))
                .andExpect(jsonPath("$.paymentType").value(PaymentType.CREDIT_CARD.toString()))
                .andExpect(jsonPath("$.date").value(date))
                .andExpect(jsonPath("$.typeOfTransaction").value(TypeOfTransaction.INCOME.toString()));
    }

    @Test
    void addTransaction() throws Exception {
        User mockUser = new User("Leslie", "leslie@gmail.com", "test123");
        mockUser.setId(1);

        Category mockCategory = new Category("gift", "first year anniversary");
        mockCategory.setId(1);

        Date date = Date.from(Instant.now());
        Transaction mockTransaction = new Transaction(
                mockUser,
                mockCategory,
                1000D,
                PaymentType.CASH,
                date,
                null,
                TypeOfTransaction.EXPENSE
        );
        mockTransaction.setId(1);

        when(transactionService.findCategoryById(1)).thenReturn(mockCategory);
        when(transactionService.findIdByUsername(anyString())).thenReturn(1);
        when(transactionService.findUserById(1)).thenReturn(mockUser);
        when(transactionService.saveTransaction(any(Transaction.class))).thenReturn(mockTransaction);

        String requestPayload = """
            {
                "category": {
                    "id": 1
                },
                "amount": 1000,
                "paymentType": "CASH",
                "typeOfTransaction": "EXPENSE"
            }
            """;

        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.amount").value(1000))
                .andExpect(jsonPath("$.paymentType").value("CASH"))
                .andExpect(jsonPath("$.typeOfTransaction").value("EXPENSE"))
                .andExpect(jsonPath("$.category.id").value(1));
    }


    @Test
    void deleteUser() throws Exception {
        User user = new User("Leslie", "leslie@gmail.com", "test123");

        Category category = new Category("gift", "first year anniversary");

        Date date = Date.from(Instant.now());

        Transaction transaction = new Transaction(user, category, 1000D, PaymentType.CASH, date, null, TypeOfTransaction.EXPENSE);

        when(transactionService.findTransactionById(0)).thenReturn(transaction);
        doNothing().when(transactionService).deleteTransaction(0);

        mockMvc.perform(delete("/api/transaction/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}