package com.example.argent;

public class Expense {
    //instance variables
    private String detail;
    private double amount;
    private String date;

    //constructors
    public Expense(String detail, double amount, String date) {
        this.detail = detail;
        this.amount = amount;
        this.date = date;
    }

    //accessors
    public String getDetail() { return this.detail; }
    public double getAmount() { return this.amount; }
    public String getDate() { return this.date; }

    //mutators
    public void setDetail(String detail) { this.detail = detail; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }

    //other methods
    public double getFinalBalance(double initialBalance, Expense expense) {
        initialBalance -= expense.getAmount();
        return initialBalance;
    }


}
