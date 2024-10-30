package com.expensetracker.expense_tracker.entity;

public enum PaymentType {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    BANK_TRANSFER("Bank Transfer"),
    CASH("Cash"),
    CRYPTO("Cryptocurrency");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
