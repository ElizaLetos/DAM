package com.expensetracker.expense_tracker.entity;

public enum TypeOfTransaction {
    INCOME, EXPENSE;

    @Override
    public String toString() {
        return switch (this) {
            case INCOME -> "INCOME";
            case EXPENSE -> "EXPENSE";
        };
    }
}


