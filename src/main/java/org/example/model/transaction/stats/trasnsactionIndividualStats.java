package org.example.model.transaction.stats;

import java.util.List;

public class trasnsactionIndividualStats {
    private List<transactionCategoryModel> income;
    private List<transactionCategoryModel> expense;

    public trasnsactionIndividualStats() {
    }

    public trasnsactionIndividualStats(List<transactionCategoryModel> income, List<transactionCategoryModel> expense) {
        this.income = income;
        this.expense = expense;
    }

    public List<transactionCategoryModel> getIncome() {
        return income;
    }

    public void setIncome(List<transactionCategoryModel> income) {
        this.income = income;
    }

    public List<transactionCategoryModel> getExpense() {
        return expense;
    }

    public void setExpense(List<transactionCategoryModel> expense) {
        this.expense = expense;
    }
}
