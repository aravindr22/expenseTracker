package org.example.model.user;

import java.util.List;

public class allCategory {
    /*private expenseCategory expense;
    private incomeCategory income;

    public allCategory() {
    }

    public allCategory(expenseCategory expense, incomeCategory income) {
        this.expense = expense;
        this.income = income;
    }

    public expenseCategory getExpense() {
        return expense;
    }

    public void setExpense(expenseCategory expense) {
        this.expense = expense;
    }

    public incomeCategory getIncome() {
        return income;
    }

    public void setIncome(incomeCategory income) {
        this.income = income;
    }*/

    private List<category> incomeCategory;
    private List<category> expenseCategory;

    public allCategory() {
    }

    public allCategory(List<category> incomeCategory, List<category> expenseCategory) {
        this.incomeCategory = incomeCategory;
        this.expenseCategory = expenseCategory;
    }

    public List<category> getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(List<category> incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public List<category> getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(List<category> expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
