package com.example.argent;

public class Expense {
    //instance variables
    private String itemPurchased;
    private double amount;
    private String date;
    private String recurringExpense;

    //constructors
    public Expense(String itemPurchased, double amount, String date, String recurringExpense) {
        this.itemPurchased = itemPurchased;
        this.amount = amount;
        this.date = date;
        this.recurringExpense= recurringExpense;
    }

    //accessors
    public String getItemPurchased() { return this.itemPurchased; }
    public double getAmount() { return this.amount; }
    public String getDate() { return this.date; }

    public String getRecurringExpense()
    {
        return this.recurringExpense;
    }

    //mutators
    public void setItemPurchased(String itemPurchased) { this.itemPurchased = itemPurchased; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }
    public void setRecurringExpense(String recurringExpense) {
        this.recurringExpense = recurringExpense;
    }
}