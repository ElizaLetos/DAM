package com.expensetracker.expense_tracker.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "note")
    private String note;

    public Income() {
    }

    public Income(int userId, Double amount, int categoryId, Date date, PaymentType paymentType, String note) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
        this.paymentType = paymentType;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category) {
        this.categoryId = category;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", categoryId='" + categoryId + '\'' +
                ", date=" + date +
                ", paymentType='" + paymentType + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
