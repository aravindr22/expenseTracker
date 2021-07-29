package org.example.model.transaction;

public class transactionStatsModel {
    private Integer transactionCount;
    private Integer income;
    private Integer expense;
    private Integer balance;

    public transactionStatsModel() {
    }

    public transactionStatsModel(Integer transactionCount, Integer income, Integer expense, Integer balance) {
        this.transactionCount = transactionCount;
        this.income = income;
        this.expense = expense;
        this.balance = balance;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
